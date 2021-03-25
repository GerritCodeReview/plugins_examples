// Copyright (C) 2021 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.dependson;

import com.google.gerrit.extensions.registration.DynamicMap;
import com.google.inject.AbstractModule;
import com.googlesource.gerrit.plugins.examples.dependson.extensions.DependencyResolver;

public class ApiModule extends AbstractModule {

  @Override
  protected void configure() {
    DynamicMap.mapOf(binder(), DependencyResolver.class);
  }
}
