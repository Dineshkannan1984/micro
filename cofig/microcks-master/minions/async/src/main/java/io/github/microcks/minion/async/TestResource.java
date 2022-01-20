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
package io.github.microcks.minion.async;

import io.github.microcks.domain.TestRunnerType;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Endpoint for tests launching API.
 * @author laurent
 */
@Path("/api/tests")
public class TestResource {

   /** Get a JBoss logging logger. */
   private final Logger logger = Logger.getLogger(getClass());

   @Inject
   AsyncAPITestManager asyncAPITestManager;

   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response launchTestCandidate(AsyncTestSpecification specification) {
      logger.debugf("Test AsyncAPI Spec: " + specification.getAsyncAPISpec());
      if (specification.getRunnerType() == TestRunnerType.ASYNC_API_SCHEMA) {
         logger.info("Accepting an ASYNC_API_SCHEMA test on endpoint " + specification.getEndpointUrl());
         asyncAPITestManager.launchTest(specification);
         return Response.accepted().build();
      }
      logger.errorf("Found no suitable test runner for {%S}, returning 204", specification.getRunnerType());
      return Response.noContent().build();
   }
}
