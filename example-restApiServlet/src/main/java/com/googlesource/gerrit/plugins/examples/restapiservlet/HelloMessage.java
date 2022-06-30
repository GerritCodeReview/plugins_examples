package com.googlesource.gerrit.plugins.examples.restapiservlet;

public class HelloMessage {

  private final String language;
  private final String message;

  public HelloMessage(String language, String message) {
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
