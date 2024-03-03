package com.y.back.errors;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found.");
  }
}
