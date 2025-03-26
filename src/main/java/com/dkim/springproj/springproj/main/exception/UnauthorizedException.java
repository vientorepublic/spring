package com.dkim.springproj.springproj.main.exception;

public class UnauthorizedException extends RuntimeException {
  private String message;

  public UnauthorizedException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
