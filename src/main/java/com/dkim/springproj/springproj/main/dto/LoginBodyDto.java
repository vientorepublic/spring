package com.dkim.springproj.springproj.main.dto;

public class LoginBodyDto {
  private String id;
  private String password;

  public LoginBodyDto(String id, String password) {
    this.id = id;
    this.password = password;
  }

  public String getId() {
    return this.id;
  }

  public String getPassword() {
    return this.password;
  }
}
