package com.dkim.springproj.springproj.main.dto;

public class MessageDto {
  String message;

  public MessageDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
