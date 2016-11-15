// Copyright (C) 2015 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.examples.validationlistenercommit;

import com.google.common.collect.ImmutableList;
import com.google.gerrit.server.events.CommitReceivedEvent;
import com.google.gerrit.server.git.validators.CommitValidationException;
import com.google.gerrit.server.git.validators.CommitValidationListener;
import com.google.gerrit.server.git.validators.CommitValidationMessage;

import java.util.List;

public class CommitValidator implements CommitValidationListener {
  @Override
  public List<CommitValidationMessage> onCommitReceived(
      CommitReceivedEvent receiveEvent) throws CommitValidationException {
    if ("plugins/example-validationListenerCommit".equals(receiveEvent.project.getName()) &&
        !receiveEvent.commit.getShortMessage().startsWith("Cookbook: ")) {
      CommitValidationMessage m = new CommitValidationMessage(
          "Subject should begin with 'Cookbook: '", true);
      throw new CommitValidationException(
          "Invalid commit message", ImmutableList.of(m));
    }
    return ImmutableList.of();
  }
}
