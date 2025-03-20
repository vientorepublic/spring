package com.dkim.springproj.springproj.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LoginBodyDto {
  @NotNull
  @NotBlank
  private String userId;

  @NotNull
  @NotBlank
  private String password;

  public LoginBodyDto(String userId, String password) {
    this.userId = userId;
    this.password = password;
  }

  public String getUserId() {
    return this.userId;
  }

  public String getPassword() {
    return this.password;
  }
}
