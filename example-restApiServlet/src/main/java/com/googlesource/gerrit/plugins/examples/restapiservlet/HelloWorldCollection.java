// Copyright (C) 2013 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.examples.restapiservlet;

import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.extensions.restapi.IdString;
import com.google.gerrit.extensions.restapi.ResourceNotFoundException;
import com.google.gerrit.extensions.restapi.ChildCollection;
import com.google.gerrit.extensions.restapi.RestView;
import com.google.gerrit.extensions.restapi.TopLevelResource;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class HelloWorldCollection implements ChildCollection<TopLevelResource, HelloResource> {
  private static final Logger log = LoggerFactory.getLogger(HelloWorldRestApiServlet.class);

  private final DynamicMap<RestView<HelloResource>> views;
  private final Provider<ListHelloMessages> list;

  @Inject
  public HelloWorldCollection(
    DynamicMap<RestView<HelloResource>> views, Provider<ListHelloMessages> list) {
      this.views = views;
      this.list = list;
  }

  @Override
  public  RestView<TopLevelResource> list() throws ResourceNotFoundException {
    log.info("HelloWorldCollection.list()");
    return list.get();
  }

  @Override
  public HelloResource parse(TopLevelResource root, IdString id)
	  throws ResourceNotFoundException {
    log.info("HelloWorldCollection.parse({}, {})", root, id);
    return new HelloResource(new HelloMessage("german", "hallo!"));
  }

  @Override
  public DynamicMap<RestView<HelloResource>> views() {
    log.info("HelloWorldCollection.views()");
    return views;
  }
}
