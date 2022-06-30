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

package com.googlesource.gerrit.plugins.examples.restapiservlet;

import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestReadView;
import com.google.gerrit.extensions.restapi.TopLevelResource;

import java.util.ArrayList;
import java.util.Collection;

public class ListHelloMessages implements RestReadView<TopLevelResource> {

  @Override
  public Response<Collection<HelloMessage>> apply(TopLevelResource resource) {
    Collection<HelloMessage> out = new ArrayList<>(3);
    out.add(new HelloMessage("english", "hello world"));
    out.add(new HelloMessage("french", "bonjour tout monde"));
    out.add(new HelloMessage("german", "Hallo Welt"));
    return Response.ok(out);
  }
}
