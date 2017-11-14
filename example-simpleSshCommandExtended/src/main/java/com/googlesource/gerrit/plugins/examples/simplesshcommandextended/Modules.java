// Copyright (C) 2018 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.simplesshcommandextended;

import com.google.gerrit.extensions.annotations.Exports;
import com.google.gerrit.server.DynamicOptions;
import com.google.gerrit.sshd.PluginCommandModule;
import java.util.Collections;

public class Modules {
  public static class SshModule extends PluginCommandModule {
    @Override
    protected void configureCommands() {
      bind(DynamicOptions.DynamicBean.class)
          .annotatedWith(
              Exports.named(
                  "com.googlesource.gerrit.plugins.examples.simplesshcommand.PrintHelloWorldCommand"))
          .to(CopyChangesOptionsClassNameProvider.class);
    }
  }

  static class CopyChangesOptionsClassNameProvider
      implements DynamicOptions.ModulesClassNamesProvider {
    @Override
    public String getClassName() {
      return "com.googlesource.gerrit.plugins.examples.simplesshcommandextended.HelloFromSomeone";
    }

    @Override
    public Iterable<String> getModulesClassNames() {
      return Collections.singleton(
          "com.googlesource.gerrit.plugins.examples.simplesshcommandextended.HelloFromSomeone$HelloFromSomeoneModule");
    }
  }
}
