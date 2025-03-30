package com.dkim.springproj.springproj.main.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBodyDto {
  @NotNull
  @NotBlank
  private String title;

  @NotNull
  @NotBlank
  private String content;
}
