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

package com.googlesource.gerrit.plugins.examples.sshandrestcommandoptionbeanparselistener;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.httpd.plugins.HttpPluginModule;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.server.query.change.QueryChanges;
import com.google.gerrit.sshd.commands.Query;
import com.google.inject.AbstractModule;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Modules {

  public static class HttpModule extends HttpPluginModule {
    @Override
    protected void configureServlets() {
      bind(DynamicOptions.DynamicBean.class)
          .annotatedWith(Exports.named(QueryChanges.class))
          .to(QueryOptions.class);
    }
  }

  public static class SshModule extends AbstractModule {
    @Override
    protected void configure() {
      bind(DynamicOptions.DynamicBean.class)
          .annotatedWith(Exports.named(Query.class))
          .to(QueryOptions.class);
    }
  }

  public static class QueryOptions
      implements DynamicOptions.DynamicBean, DynamicOptions.BeanParseListener {
    private static final Logger log = LoggerFactory.getLogger(QueryOptions.class);

    private Object bean;

    @Option(
        name = "--log",
        aliases = {"-l"},
        usage = "Say Hello in the Log a few times")
    private void parse(String arg) {
      String msg = "Hellow unknown api query user: " + arg;
      if (bean instanceof Query) {
        msg = "Hello ssh api query user: " + arg;
      } else if (bean instanceof QueryChanges) {
        msg = "Hello rest api query user: " + arg;
      }
      log.error(msg);
    }

    @Override
    public void onBeanParseStart(String plugin, Object bean) {
      this.bean = bean;
      log.error("hello - parse start");
    }

    @Override
    public void onBeanParseEnd(String plugin, Object bean) {
      log.error("hello - parse end");
    }
  }
}
