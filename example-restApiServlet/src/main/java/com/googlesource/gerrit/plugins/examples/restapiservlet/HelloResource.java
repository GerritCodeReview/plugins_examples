package com.googlesource.gerrit.plugins.examples.restapiservlet;

import com.google.gerrit.extensions.restapi.RestResource;
import com.google.gerrit.extensions.restapi.RestView;
import com.google.inject.TypeLiteral;

public class HelloResource implements RestResource {

  public static final TypeLiteral<RestView<HelloResource>> HELLO_KIND =
      new TypeLiteral<RestView<HelloResource>>() {};

  private final String language;
  private final String message;

  public HelloResource(String language, String message) {
    this.language = language;
    this.message = message;
  }

  public String getLanguage() {
	  return language;
  }
  public String getMessage() {
	  return message;
  }
}
