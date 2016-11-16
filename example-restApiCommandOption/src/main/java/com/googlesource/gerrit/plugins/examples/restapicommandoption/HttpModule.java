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

package com.googlesource.gerrit.plugins.examples.restapicommandoption;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.httpd.plugins.HttpPluginModule;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.server.query.change.QueryChanges;

import org.kohsuke.args4j.Option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpModule extends HttpPluginModule {
  private static final Logger log = LoggerFactory.getLogger(HttpModule.class);

  @Override
  protected void configureServlets() {
    bind(DynamicOptions.DynamicBean.class).annotatedWith(
        Exports.named(QueryChanges.class)).to(MyOptions.class);
  }

  public static class MyOptions implements DynamicOptions.DynamicBean {
    @Option(name = "--log", aliases = { "-l" }, usage = "Say Hello in the Log")
    private void parse(String arg) {
      log.error("Say Hello in the Log " + arg);
    }
  }
}
