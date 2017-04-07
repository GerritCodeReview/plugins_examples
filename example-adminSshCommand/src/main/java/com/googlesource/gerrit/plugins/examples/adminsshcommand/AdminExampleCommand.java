// Copyright (C) 2014 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.adminsshcommand;

import com.google.gerrit.extensions.annotations.RequiresCapability;
import com.google.gerrit.sshd.CommandMetaData;
import com.google.gerrit.sshd.SshCommand;
import org.kohsuke.args4j.Option;

@RequiresCapability(AdminExampleCapability.ADMIN_EXAMPLE)
@CommandMetaData(name = "admin", description = "Administrate the example")
public final class AdminExampleCommand extends SshCommand {
  private int count = 1;

  @Option(
    name = "--count",
    aliases = {"-c"},
    metaVar = "COUNT",
    usage = "Number of times to greet the administrator"
  )
  public void setCount(int count) {
    this.count = count;
  }

  @Override
  protected void run() {
    while (count-- > 0) {
      stdout.print("Hello, example administrator\n");
    }
  }
}
