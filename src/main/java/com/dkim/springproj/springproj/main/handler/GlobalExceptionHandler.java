package com.dkim.springproj.springproj.main.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.dkim.springproj.springproj.main.dto.ErrorDto;
import com.dkim.springproj.springproj.main.exception.EmptyParamException;
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

  @ExceptionHandler(EmptyParamException.class)
  private ResponseEntity<ErrorDto> BadRequestException() {
    String now = this.utility.getISOTimestamp();
    ErrorDto res = new ErrorDto(now, 400, "Bad Request", "필수 입력값이 누락되었습니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }
}
