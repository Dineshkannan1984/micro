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
package io.github.microcks.config;

import io.github.microcks.util.UsernamePasswordProxyAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.net.Authenticator;

/**
 * @author laurent
 */
@Component
public class ProxyConfiguration {

   /** A simple logger for diagnostic messages. */
   private static Logger log = LoggerFactory.getLogger(ProxyConfiguration.class);

   @Value("${network.proxyHost}")
   private final String proxyHost = null;

   @Value("${network.nonProxyHosts}")
   private final String nonProxyHosts = null;

   @Value("${network.proxyPort}")
   private final Integer proxyPort = null;

   @Value("${network.proxyUsername}")
   private final String proxyUsername = null;

   @Value("${network.proxyPassword}")
   private final String proxyPassword = null;

   @Bean
   public ProxySettings buildProxySettings() {
      // Initialize ProxySettings if provided.
      if (proxyHost != null && proxyPort != null) {
         log.info("Configuring HTTP(S) proxy to {}:{}", proxyHost, proxyPort);
         System.setProperty("http.proxyHost", proxyHost);
         System.setProperty("http.proxyPort", proxyPort.toString());
         System.setProperty("http.nonProxyHosts", nonProxyHosts);
         System.setProperty("https.proxyHost", proxyHost);
         System.setProperty("https.proxyPort", proxyPort.toString());
         System.setProperty("https.nonProxyHosts", nonProxyHosts);

         // From jdk 8.111+ we also need to remove disabled schemes to allow Basic authentication
         // for HTTPS for example (see http://www.oracle.com/technetwork/java/javase/8u111-relnotes-3124969.html).
         if (proxyUsername != null && proxyPassword != null) {
            System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
            System.setProperty("jdk.http.auth.proxying.disabledSchemes", "");
         }

         // Build settings and set Authenticator for the whole JVM.
         ProxySettings settings = new ProxySettings(proxyHost, proxyPort, proxyUsername, proxyPassword);
         Authenticator.setDefault(new UsernamePasswordProxyAuthenticator(settings));

         return settings;
      }
      return null;
   }
}
