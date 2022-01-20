/*
 * Licensed to Laurent Broudoux (the "Author") under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. Author licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package io.github.microcks.util;

import io.github.microcks.domain.Secret;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper object that can be used to fully resolved references that are placed within
 * a specification or a schema (think of the $ref within OpenAPI or AsyncAPI documents).
 * A resolver is built with a base repository URL (that should point to a "folder") and
 * some security parameters on how to access this repository. It then takes care of
 * retrieving reference content using their relative path from base repository URL.
 * @author laurent
 */
public class ReferenceResolver {

   /** A simple logger for diagnostic messages. */
   private static Logger log = LoggerFactory.getLogger(ReferenceResolver.class);

   private String baseRepositoryUrl;
   private Secret repositorySecret;
   private boolean disableSSLValidation;
   private Map<String, File> resolvedReferences = new HashMap<>();

   /**
    * Build a new reference resolver.
    * @param baseRepositoryUrl The root folder representing a remote repository we want to resolved references to
    * @param repositorySecret An optional Secret containing connection credentials to the repository
    * @param disableSSLValidation Whether to disable or enable the SSL trusting of certificates
    */
   public ReferenceResolver(String baseRepositoryUrl, Secret repositorySecret, boolean disableSSLValidation) {
      this.repositorySecret = repositorySecret;
      this.disableSSLValidation = disableSSLValidation;
      // Remove trailing / to ease things later.
      if (baseRepositoryUrl.endsWith("/")) {
         this.baseRepositoryUrl = baseRepositoryUrl.substring(0, baseRepositoryUrl.length() - 1);
      } else {
         this.baseRepositoryUrl = baseRepositoryUrl;
      }
   }

   /**
    * Retrieve a reference content from remote repository using its relative path?
    * @param relativePath The relative path of the reference to retrieve
    * @param encoding The encoding to use for building a string representation of content.
    * @return A string representation of reference content.
    * @throws IOException if access to remote reference fails (not found or connection issues)
    */
   public String getHttpReferenceContent(String relativePath, String encoding) throws IOException {
      // Check the file first.
      File referenceFile = resolvedReferences.get(relativePath);
      if (referenceFile == null) {
         // Rebuild a downloadable URL to retrieve file.
         String remoteUrl = baseRepositoryUrl;
         String pathToAppend = relativePath;
         while (pathToAppend.startsWith("../")) {
            remoteUrl = remoteUrl.substring(0, remoteUrl.lastIndexOf("/"));
            pathToAppend = pathToAppend.substring(3);
         }
         if (pathToAppend.startsWith("./")) {
            pathToAppend = pathToAppend.substring(2);
         }
         if (pathToAppend.startsWith("/")) {
            pathToAppend = pathToAppend.substring(1);
         }
         remoteUrl += "/" + pathToAppend;
         log.info("Downloading a reference file at {}", remoteUrl);
         // Now download this relative file and store its reference into the cache.
         referenceFile = HTTPDownloader.handleHTTPDownloadToFile(remoteUrl, repositorySecret, disableSSLValidation);
         resolvedReferences.put(relativePath, referenceFile);
      }

      return Files.readString(referenceFile.toPath(), Charset.forName(encoding));
   }

   /**
    * Get resolved references map.
    * @return The map of resolved references
    */
   public Map<String, File> getResolvedReferences() {
      return resolvedReferences;
   }

   /** Cleans up already resolved references. */
   public void cleanResolvedReferences() {
      for (File referenceFile : resolvedReferences.values()) {
         referenceFile.delete();
      }
      resolvedReferences.clear();
   }
}
