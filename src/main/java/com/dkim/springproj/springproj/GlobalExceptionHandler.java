package com.dkim.springproj.springproj;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.dkim.springproj.springproj.main.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoResourceFoundException.class)
  protected ResponseEntity<ErrorDto> NotFoundException() {
    ErrorDto res = new ErrorDto(404, "Not Found", "Request path not found.");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }
}
