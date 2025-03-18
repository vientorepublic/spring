package com.dkim.springproj.springproj.main.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.dkim.springproj.springproj.main.dto.ErrorDto;
import com.dkim.springproj.springproj.main.exception.BadRequestException;
import com.dkim.springproj.springproj.main.utility.Utility;

@ControllerAdvice
public class GlobalExceptionHandler {
  private Utility utility = new Utility();

  @ExceptionHandler(NoResourceFoundException.class)
  private ResponseEntity<ErrorDto> NotFoundException() {
    String now = this.utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 404, "Not Found", "요청하신 주소를 찾을 수 없습니다.");
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  private ResponseEntity<ErrorDto> RequestParamException(MissingServletRequestParameterException ex) {
    String now = this.utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 400, "Bad Request", "필수 인자가 누락되었습니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler(BadRequestException.class)
  private ResponseEntity<ErrorDto> BadRequestException(BadRequestException ex) {
    String now = this.utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 400, "Bad Request", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }
}
