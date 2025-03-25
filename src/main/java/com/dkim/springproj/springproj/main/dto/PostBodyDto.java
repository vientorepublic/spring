package com.dkim.springproj.springproj.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PostBodyDto {
  @NotNull
  @NotBlank
  private String title;
  @NotNull
  @NotBlank
  private String content;

  public PostBodyDto(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }
}
