package com.googlesource.gerrit.plugins.examples.restapiservlet;

import com.google.gerrit.extensions.restapi.RestResource;
import com.google.gerrit.extensions.restapi.RestView;
import com.google.inject.TypeLiteral;

public class HelloResource implements RestResource {
  public static final TypeLiteral<RestView<HelloResource>> HELLO_KIND =
      new TypeLiteral<RestView<HelloResource>>() {};

  private final HelloMessage message;

  public HelloResource(HelloMessage message) {
    this.message = message;
  }

  public HelloMessage getMessage() {
    return message;
  }

}
