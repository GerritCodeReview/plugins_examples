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

package com.googlesource.gerrit.plugins.examples.validationlistenerrefoperation;

import com.google.common.collect.Lists;
import com.google.gerrit.reviewdb.client.RefNames;
import com.google.gerrit.server.events.RefReceivedEvent;
import com.google.gerrit.server.git.validators.RefOperationValidationListener;
import com.google.gerrit.server.git.validators.ValidationMessage;
import com.google.gerrit.server.permissions.GlobalPermission;
import com.google.gerrit.server.permissions.PermissionBackend;
import com.google.gerrit.server.validators.ValidationException;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RefOperationValidationExample implements RefOperationValidationListener {

  private PermissionBackend permissionBackend;

  @Inject
  RefOperationValidationExample(PermissionBackend permissionBackend) {
    this.permissionBackend = permissionBackend;
  }

  @Override
  public List<ValidationMessage> onRefOperation(RefReceivedEvent event) throws ValidationException {
    ArrayList<ValidationMessage> messages = Lists.newArrayList();
    if (event.command.getRefName().startsWith(RefNames.REFS_HEADS + "protected-")
        && !permissionBackend.user(event.user).testOrFalse(GlobalPermission.ADMINISTRATE_SERVER)) {
      throw new ValidationException(
          String.format(
              "Operation %s on %s branch in project %s is not valid!",
              event.command.getType(), event.command.getRefName(), event.project.getName()));
    }
    return messages;
  }
}
