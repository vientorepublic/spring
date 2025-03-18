package com.dkim.springproj.springproj.main.exception;

public class NotFoundException extends RuntimeException {
  private String message;

  public NotFoundException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
