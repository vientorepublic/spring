package com.dkim.springproj.springproj;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.dkim.springproj.springproj.main.dto.ErrorDto;
import com.dkim.springproj.springproj.main.utility.Utility;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoResourceFoundException.class)
  private ResponseEntity<ErrorDto> NotFoundException() {
    String now = new Utility().getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 404, "Not Found", "Request path not found.");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }
}
