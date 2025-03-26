package com.dkim.springproj.springproj.main.dto;

public class AuthResponseDto extends MessageDto {
  private String token;

  public AuthResponseDto(String message, String token) {
    super(message);
    this.token = token;
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
