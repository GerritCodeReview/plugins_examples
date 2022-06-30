package com.googlesource.gerrit.plugins.examples.restapiservlet;

import com.google.gerrit.extensions.restapi.Response;
import com.google.gerrit.extensions.restapi.RestReadView;

public class GetHello implements RestReadView<HelloResource> {
  @Override
  public Response<HelloMessage> apply(HelloResource resource) {
    return Response.ok(resource.getMessage());
  }
}
