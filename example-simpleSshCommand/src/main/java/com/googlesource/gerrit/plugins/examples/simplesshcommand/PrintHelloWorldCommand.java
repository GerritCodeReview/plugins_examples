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

package com.googlesource.gerrit.plugins.examples.simplesshcommand;

import com.google.gerrit.sshd.CommandMetaData;
import com.google.gerrit.sshd.SshCommand;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;

@CommandMetaData(name = "print", description = "Print greeting in different languages")
public final class PrintHelloWorldCommand extends SshCommand {

  @Argument(usage = "name of user")
  private String name = "world";

  @Option(name = "--french", usage = "output in French?")
  private boolean french;

  @Override
  public void run() {
    final String greeting = (french ? "Bonjour " : "Hello ");
    // Note the use of '\n' instead of println to keep platform-agnostic line
    // terminator.
    stdout.print(greeting + name + "!\n");
  }
}
