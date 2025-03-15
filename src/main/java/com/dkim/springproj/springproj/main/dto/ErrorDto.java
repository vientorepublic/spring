package com.dkim.springproj.springproj.main.dto;

public class ErrorDto {
  private Number status;
  private String error;
  private String message;

  public ErrorDto(Number status, String error, String message) {
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public Number getStatus() {
    return this.status;
  }

  public String getError() {
    return this.error;
  }

  public String getMessage() {
    return this.message;
  }
}
