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

package com.googlesource.gerrit.plugins.examples.validationlistenermerge;

import com.google.gerrit.entities.BranchNameKey;
import com.google.gerrit.entities.PatchSet;
import com.google.gerrit.server.IdentifiedUser;
import com.google.gerrit.server.git.CodeReviewCommit;
import com.google.gerrit.server.git.validators.MergeValidationException;
import com.google.gerrit.server.git.validators.MergeValidationListener;
import com.google.gerrit.server.permissions.GlobalPermission;
import com.google.gerrit.server.permissions.PermissionBackend;
import com.google.gerrit.server.project.ProjectState;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.eclipse.jgit.lib.Repository;

// Because we have a dedicated Module, we need to bind to the set
// there, however, if you are using this as a base for your own
// plugin, you can simply comment out the 'Listen' annotation and
// it will work as expected.
// @Listen
@Singleton
public class MergeUserValidator implements MergeValidationListener {

  private PermissionBackend permissionBackend;

  @Inject
  MergeUserValidator(PermissionBackend permissionBackend) {
    this.permissionBackend = permissionBackend;
  }

  /** Reject all merges if the submitter is not an administrator */
  @Override
  public void onPreMerge(
      Repository repo,
      CodeReviewCommit commit,
      ProjectState destProject,
      BranchNameKey destBranch,
      PatchSet.Id patchSetId,
      IdentifiedUser caller)
      throws MergeValidationException {

    if (!permissionBackend.user(caller).testOrFalse(GlobalPermission.ADMINISTRATE_SERVER)) {
      throw new MergeValidationException(
          "Submitter " + caller.getNameEmail() + " is not a site administrator");
    }
  }
}
