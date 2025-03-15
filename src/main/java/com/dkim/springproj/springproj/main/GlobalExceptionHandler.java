package com.dkim.springproj.springproj.main;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.dkim.springproj.springproj.main.dto.ErrorDto;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(NoHandlerFoundException.class)
  public ErrorDto NotFoundException(NoHandlerFoundException ex) {
    return new ErrorDto(404, "Not Found", "Request path not found.");
  }
}
