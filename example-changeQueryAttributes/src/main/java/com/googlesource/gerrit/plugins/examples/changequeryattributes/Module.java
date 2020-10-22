// Copyright (C) 2017 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.changequeryattributes;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.extensions.registration.DynamicSet;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.server.change.ChangePluginDefinedInfoFactory;
import com.google.gerrit.server.restapi.change.GetChange;
import com.google.gerrit.server.restapi.change.QueryChanges;
import com.google.gerrit.sshd.commands.Query;
import com.google.inject.AbstractModule;

public class Module extends AbstractModule {
  @Override
  protected void configure() {
    // Register attribute factory.
    DynamicSet.bind(binder(), ChangePluginDefinedInfoFactory.class).to(AttributeFactory.class);

    // Register options for GET /changes/X/change and /changes/X/detail.
    bind(DynamicOptions.DynamicBean.class)
        .annotatedWith(Exports.named(GetChange.class))
        .to(AttributeFactory.MyChangeOptions.class);

    // Register options for GET /changes/?q=...
    bind(DynamicOptions.DynamicBean.class)
        .annotatedWith(Exports.named(QueryChanges.class))
        .to(AttributeFactory.MyChangeOptions.class);

    // Register options for ssh gerrit query.
    bind(DynamicOptions.DynamicBean.class)
        .annotatedWith(Exports.named(Query.class))
        .to(AttributeFactory.MyChangeOptions.class);
  }
}
