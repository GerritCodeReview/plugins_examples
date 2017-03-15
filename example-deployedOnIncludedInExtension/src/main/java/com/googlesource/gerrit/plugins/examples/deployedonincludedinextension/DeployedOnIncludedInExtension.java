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

package com.googlesource.gerrit.plugins.examples.deployedonincludedinextension;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;
import com.google.gerrit.extensions.config.ExternalIncludedIn;
import java.util.Collection;

public class DeployedOnIncludedInExtension implements ExternalIncludedIn {
  private static final String PROD = "Production";
  private static final String STAGING = "Staging";

  @Override
  public ListMultimap<String, String> getIncludedIn(
      String project, String commit, Collection<String> tags, Collection<String> branches) {
    ListMultimap<String, String> m = MultimapBuilder.hashKeys().arrayListValues().build();
    m.put(PROD, "A");
    m.put(PROD, "B");
    m.put(PROD, "C");
    m.put(STAGING, "X");
    m.put(STAGING, "Y");
    m.put(STAGING, "Z");
    return m;
  }
}
