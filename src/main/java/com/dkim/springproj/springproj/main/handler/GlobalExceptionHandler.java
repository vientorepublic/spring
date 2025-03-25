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
import com.dkim.springproj.springproj.main.exception.InternalServerException;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.utility.Utility;

@ControllerAdvice
public class GlobalExceptionHandler {
  private final Utility utility = new Utility();

  private ResponseEntity<ExceptionDto> buildResponse(HttpStatus status, String error, String message) {
    String now = utility.getISOTimestamp();
    ExceptionDto res = new ExceptionDto(now, status.value(), error, message);
    return ResponseEntity.status(status).body(res);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  private ResponseEntity<ExceptionDto> handleMissingRequestParameter() {
    return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", "필수 인자가 누락되었습니다.");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  private ResponseEntity<ExceptionDto> handleHttpMessageNotReadable() {
    return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", "필수 인자가 누락되었습니다.");
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  private ResponseEntity<ExceptionDto> handleMethodArgumentNotValid() {
    return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", "필수 인자가 누락되었습니다.");
  }

  @ExceptionHandler(NotFoundException.class)
  private ResponseEntity<ExceptionDto> handleNotFound(NotFoundException ex) {
    return buildResponse(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  private ResponseEntity<ExceptionDto> handleBadRequest(BadRequestException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
  }

  @ExceptionHandler(UnauthorizedException.class)
  private ResponseEntity<ExceptionDto> handleUnauthorized(UnauthorizedException ex) {
    return buildResponse(HttpStatus.UNAUTHORIZED, "Unauthorized", ex.getMessage());
  }

  @ExceptionHandler(InternalServerException.class)
  private ResponseEntity<ExceptionDto> handleInternalServerError(InternalServerException ex) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", ex.getMessage());
  }
}
