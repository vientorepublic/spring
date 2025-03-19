package com.dkim.springproj.springproj.main.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.dkim.springproj.springproj.main.dto.ErrorDto;
import com.dkim.springproj.springproj.main.exception.BadRequestException;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.utility.Utility;

@ControllerAdvice
public class GlobalExceptionHandler {
  private final Utility utility = new Utility();

  // Global Not Found Exception
  @ExceptionHandler(NoResourceFoundException.class)
  private ResponseEntity<ErrorDto> NotFoundException() {
    String now = utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 404, "Not Found", "요청하신 주소를 찾을 수 없습니다.");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }

  // Global Missing Request Parmeter Exception
  @ExceptionHandler(MissingServletRequestParameterException.class)
  private ResponseEntity<ErrorDto> RequestParamException(MissingServletRequestParameterException ex) {
    String now = utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 400, "Bad Request", "필수 인자가 누락되었습니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  // Custom Not Found Exception
  @ExceptionHandler(NotFoundException.class)
  private ResponseEntity<ErrorDto> NotFoundException(NotFoundException ex) {
    String now = utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 404, "Not Found", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }

  // Custom Bad Request Exception
  @ExceptionHandler(BadRequestException.class)
  private ResponseEntity<ErrorDto> BadRequestException(BadRequestException ex) {
    String now = utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 400, "Bad Request", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }
}
