package com.dkim.springproj.springproj.main.dto;

public class MessageDto {
  private String message;

  public MessageDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
