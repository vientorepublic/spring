package com.dkim.springproj.springproj.main.dto;

public class ExceptionDto {
  private String timestamp;
  private Number status;
  private String error;
  private String message;

  public ExceptionDto(String timestamp, Number status, String error, String message) {
    this.timestamp = timestamp;
    this.status = status;
    this.error = error;
    this.message = message;
  }

  public String getTimestamp() {
    return this.timestamp;
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
