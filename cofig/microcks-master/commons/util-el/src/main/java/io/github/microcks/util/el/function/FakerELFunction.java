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
package io.github.microcks.util.el.function;

import com.github.javafaker.Faker;
import io.github.microcks.util.el.EvaluationContext;

/**
 * This is a base class for function that are using Java Faker library
 * (see https://github.com/DiUS/java-faker). This base class provides a convenient method
 * for retrieving or lazy loading a Faker that will be put into {@code EvaluationContext}.
 * @author laurent
 */
public abstract class FakerELFunction implements ELFunction {

   protected static final String FAKER_VARIABLE_NAME = "faker";

   /**
    * Retrieve a Faker from evaluation content. Lazy load it if not already present.
    * @param evaluationContext The context to retrieve from or store within
    * @return A Faker implementation ready to use.
    */
   protected Faker retrieveFaker(EvaluationContext evaluationContext) {
      Faker faker = null;
      Object fakerObject = evaluationContext.lookupVariable(FAKER_VARIABLE_NAME);
      if (fakerObject != null && fakerObject instanceof Faker) {
         faker = (Faker) fakerObject;
      } else {
         // Build a new Faker and store it into context for later fakers.
         faker = new Faker();
         evaluationContext.setVariable(FAKER_VARIABLE_NAME, faker);
      }
      return faker;
   }
}
