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

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public Number getStatus() {
    return this.status;
  }

  public void setStatus(Number status) {
    this.status = status;
  }

  public String getError() {
    return this.error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
