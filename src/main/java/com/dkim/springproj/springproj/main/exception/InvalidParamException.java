package com.dkim.springproj.springproj.main.exception;

public class InvalidParamException extends RuntimeException {
  private String message;

  public InvalidParamException(String message) {
    super(message);
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
