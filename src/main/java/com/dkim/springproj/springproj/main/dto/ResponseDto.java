package com.dkim.springproj.springproj.main.dto;

public class ResponseDto {
  String message;

  public ResponseDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}
