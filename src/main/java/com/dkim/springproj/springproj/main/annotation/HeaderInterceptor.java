package com.dkim.springproj.springproj.main.annotation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import com.dkim.springproj.springproj.main.exception.InternalServerException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
  @Value("${jwt.secret}")
  private String secretKey;

  @Override
  @SuppressWarnings("null")
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (handler instanceof org.springframework.web.method.HandlerMethod) {
      org.springframework.web.method.HandlerMethod handlerMethod = (org.springframework.web.method.HandlerMethod) handler;
      AuthGuard readHeader = handlerMethod.getMethodAnnotation(AuthGuard.class);
      if (readHeader != null) {
        String token = request.getHeader("Authorization");
        if (token != null) {
          if (token.startsWith("Bearer")) {
            String jwt = token.substring(7);
            try {
              new JwtUtility().decodeToken(secretKey, jwt);
              return true;
            } catch (Exception e) {
              throw new UnauthorizedException("유효하지 않은 토큰입니다.");
            }
          } else {
            throw new UnauthorizedException("Bearer 토큰을 찾을 수 없습니다.");
          }
        } else {
          throw new UnauthorizedException("Authorization 헤더를 찾을 수 없습니다.");
        }
      } else {
        return true;
      }
    } else {
      throw new InternalServerException("Handler is not a HandlerMethod");
    }
  }
}
