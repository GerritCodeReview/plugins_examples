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

package com.googlesource.gerrit.plugins.examples.validationlistenerupload;

import com.google.gerrit.reviewdb.client.Project;
import com.google.gerrit.server.CurrentUser;
import com.google.gerrit.server.git.validators.UploadValidationListener;
import com.google.gerrit.server.validators.ValidationException;
import com.google.inject.Inject;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.UploadPack;

import java.util.Collection;

public class DenyUploadExample implements UploadValidationListener {
  @Inject
  private CurrentUser user;

  @Override
  public void onPreUpload(Repository repository, Project project,
      String remoteHost, UploadPack up, Collection<? extends ObjectId> wants,
      Collection<? extends ObjectId> haves) throws ValidationException {
    up.sendMessage("Validating project name for " + user.getUserName() + "\n");
    up.sendMessage("  from host: " + remoteHost + "\n");
    if (project.getName().equals("deny-upload-project")) {
      throw new ValidationException(
          "This error message was sent from example-validationListenerUpload plugin DenyUploadExample.");
    }
  }
}
