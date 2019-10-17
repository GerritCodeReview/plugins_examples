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

package com.googlesource.gerrit.plugins.examples.validationlistenerhashtag;

import com.google.gerrit.entities.Change;
import com.google.gerrit.server.validators.HashtagValidationListener;
import com.google.gerrit.server.validators.ValidationException;
import java.util.Set;

public class HashtagValidator implements HashtagValidationListener {

  @Override
  public void validateHashtags(Change change, Set<String> toAdd, Set<String> toRemove)
      throws ValidationException {
    if (change.getProject().get().equals("plugins/example-validationListenerHashtag")) {
      if (toAdd.size() > 0) {
        for (String hashtag : toAdd) {
          if (!hashtag.startsWith("example-validationListenerHashtag-")) {
            throw new ValidationException(
                "Invalid example-validationListenerHashtag hashtag: " + hashtag);
          }
        }
      }

      if (toRemove.size() > 0) {
        throw new ValidationException("Cannot remove example-validationListenerHashtag hashtags");
      }
    }
  }
}
