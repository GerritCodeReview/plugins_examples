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

package com.googlesource.gerrit.plugins.examples.weblinkbranch;

import com.google.gerrit.extensions.common.WebLinkInfo;
import com.google.gerrit.extensions.webui.BranchWebLink;

public class HelloWeblink implements BranchWebLink {
  private String name = "HelloLink";
  private String placeHolderUrlProjectBranch = "http://my.hellolink.com/project=%s-branch=%s";
  private String myImageUrl = "http://placehold.it/16x16.gif";

  @Override
  public WebLinkInfo getBranchWebLink(String projectName, String branchName) {
    return new WebLinkInfo(
        name,
        myImageUrl,
        String.format(placeHolderUrlProjectBranch, projectName, branchName),
        Target.BLANK);
  }
}
