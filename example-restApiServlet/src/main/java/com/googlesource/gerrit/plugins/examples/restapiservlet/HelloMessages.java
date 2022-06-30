package com.googlesource.gerrit.plugins.examples.restapiservlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class HelloMessages {

  private Collection<HelloMessage> messages;

  HelloMessages() {
    messages = new ArrayList<>(3);
    messages.add(new HelloMessage("english", "hello world"));
    messages.add(new HelloMessage("french", "bonjour tout le monde"));
    messages.add(new HelloMessage("german", "Hallo Welt"));
  }

  public Collection<HelloMessage> getAllMessages() {
    return messages;
  }

  public Optional<HelloMessage> getByLanguage(String lang) {
    return messages.stream().filter(m -> m.getLanguage().equals(lang)).findFirst();
  }
}
