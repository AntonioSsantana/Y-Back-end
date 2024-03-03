package com.y.back.errors;

public class NoPostsFound extends RuntimeException {
  public NoPostsFound(){
    super("Posts Not Found");
  }
}
