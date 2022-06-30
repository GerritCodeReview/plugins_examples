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

import com.google.inject.servlet.ServletModule;
import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.gerrit.extensions.restapi.RestApiModule;
import static com.googlesource.gerrit.plugins.examples.restapiservlet.HelloResource.HELLO_KIND;

public class HttpModule extends ServletModule {

  @Override
  protected void configureServlets() {
    bind(ListHelloMessages.class);
    bind(HelloWorldCollection.class);

    install(
			new RestApiModule() {
				@Override
				protected void configure() {
					DynamicMap.mapOf(binder(), HELLO_KIND);
					get(HELLO_KIND, "message").to(GetHello.class);
				}
			}
		);
    
    serve("/hello*").with(HelloWorldRestApiServlet.class);
  }
}
