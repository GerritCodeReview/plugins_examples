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

package com.googlesource.gerrit.plugins.examples.sshandrestcommandoptionbeanparselistner;

import com.google.gerrit.server.DynamicOptions;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryOptions implements DynamicOptions.DynamicBean, DynamicOptions.BeanParseListener {
  private static final Logger log = LoggerFactory.getLogger(QueryOptions.class);

  @Option(
    name = "--log",
    aliases = {"-l"},
    usage = "Say Hello in the Log a few times"
  )
  private void parse(String arg) {
    log.error("hello - option parse: " + arg);
  }

  @Override
  public void onBeanParseStart(String plugin, Object bean) {
    log.error("hello - parse start");
  }

  @Override
  public void onBeanParseEnd(String plugin, Object bean) {
    log.error("hello - parse end");
  }
}
