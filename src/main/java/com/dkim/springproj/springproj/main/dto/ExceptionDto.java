package com.dkim.springproj.springproj.main.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
  private String timestamp;
  private Number status;
  private String error;
  private String message;
}
