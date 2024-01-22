package com.y.back.errors;

public class NoRegisteredUsers extends RuntimeException {
  public NoRegisteredUsers() {
    super("No registered users found.");
  }
}
