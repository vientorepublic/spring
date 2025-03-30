package com.dkim.springproj.springproj.main.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
  private String message;
}
