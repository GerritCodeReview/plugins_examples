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

package com.googlesource.gerrit.plugins.examples.assigneevalidator;

import com.google.gerrit.reviewdb.client.Account;
import com.google.gerrit.reviewdb.client.Change;
import com.google.gerrit.server.query.QueryParseException;
import com.google.gerrit.server.query.change.ChangeQueryBuilder;
import com.google.gerrit.server.query.change.ChangeQueryProcessor;
import com.google.gerrit.server.validators.AssigneeValidationListener;
import com.google.gerrit.server.validators.ValidationException;
import com.google.gwtorm.server.OrmException;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AssigneeValidator implements AssigneeValidationListener {
  private static final Logger log = LoggerFactory.getLogger(AssigneeValidationListener.class);

  private static int MAX_ASSIGNED_CHANGES = 5;

  @Inject ChangeQueryBuilder queryBuilder;

  @Inject ChangeQueryProcessor queryProcessor;

  @Override
  public void validateAssignee(Change change, Account assignee) throws ValidationException {
    try {
      if (queryProcessor
              .query(queryBuilder.assignee(assignee.getPreferredEmail()))
              .entities()
              .size()
          > MAX_ASSIGNED_CHANGES) {
        throw new ValidationException(
            "Cannot assign user to more than " + MAX_ASSIGNED_CHANGES + " changes");
      }
    } catch (OrmException | QueryParseException e) {
      log.error("Failed to validate assignee for change " + change.getId(), e);
      // Allow assignee.
    }
  }
}
