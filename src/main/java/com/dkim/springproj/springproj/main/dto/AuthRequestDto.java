package com.dkim.springproj.springproj.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AuthRequestDto {
  @NotNull
  @NotBlank
  private String userId;

  @NotNull
  @NotBlank
  private String password;

  public AuthRequestDto(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }

  public String getUserId() {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
