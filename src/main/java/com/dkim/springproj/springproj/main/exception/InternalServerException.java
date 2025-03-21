package com.dkim.springproj.springproj.main.exception;

public class InternalServerException extends RuntimeException {
  private String message;

  public InternalServerException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
