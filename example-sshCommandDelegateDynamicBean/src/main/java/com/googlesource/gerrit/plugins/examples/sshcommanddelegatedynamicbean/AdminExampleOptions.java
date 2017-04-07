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

package com.googlesource.gerrit.plugins.examples.sshcommanddelegatedynamicbean;

import com.google.gerrit.server.DynamicOptions;
import com.googlesource.gerrit.plugins.examples.adminsshcommand.AdminExampleCommand;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdminExampleOptions
    implements DynamicOptions.DynamicBean, DynamicOptions.BeanParseListener {
  private final Logger log = LoggerFactory.getLogger(AdminExampleOptions.class);

  private int count = 0;
  private boolean countSet = false;

  @Option(
    name = "--repeat",
    aliases = {"-r"},
    usage = "Run the command multiple times"
  )
  private void parse(String arg) {
    count = Integer.parseInt(arg);
    countSet = true;
  }

  @Override
  public void onBeanParseStart(String plugin, Object bean) {}

  @Override
  public void onBeanParseEnd(String plugin, Object bean) {
    AdminExampleCommand cmd = (AdminExampleCommand) bean;
    if (countSet) {
      cmd.setCount(count);
    }
  }
}
