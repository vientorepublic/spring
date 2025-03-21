package com.dkim.springproj.springproj.main.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.dkim.springproj.springproj.main.dto.ExceptionDto;
import com.dkim.springproj.springproj.main.exception.BadRequestException;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.utility.Utility;
import io.jsonwebtoken.security.SignatureException;

@ControllerAdvice
public class GlobalExceptionHandler {
  private final Utility utility = new Utility();

  @ExceptionHandler(MissingServletRequestParameterException.class)
  private ResponseEntity<ExceptionDto> RequestParameterException() {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 400, "Bad Request", "필수 인자가 누락되었습니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler({ HttpMessageNotReadableException.class })
  private ResponseEntity<ExceptionDto> RequestBodyException() {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 400, "Bad Request", "필수 인자가 누락되었습니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<ExceptionDto> ArgumentNotValidException() {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 400, "Bad Request", "필수 인자가 누락되었습니다.");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler(NotFoundException.class)
  private ResponseEntity<ExceptionDto> CustomNotFoundException(NotFoundException ex) {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 404, "Not Found", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
  }

  @ExceptionHandler(BadRequestException.class)
  private ResponseEntity<ExceptionDto> BadRequestException(BadRequestException ex) {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 400, "Bad Request", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
  }

  @ExceptionHandler(UnauthorizedException.class)
  private ResponseEntity<ExceptionDto> UnauthorizedException(UnauthorizedException ex) {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 401, "Unauthorized", ex.getMessage());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
  }

  @ExceptionHandler(SignatureException.class)
  private ResponseEntity<ExceptionDto> SignatureException() {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, 401, "Unauthorized", "잘못된 토큰입니다.");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
  }
}
