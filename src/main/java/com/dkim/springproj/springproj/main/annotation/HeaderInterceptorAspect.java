package com.dkim.springproj.springproj.main.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.dkim.springproj.springproj.main.exception.InternalServerException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HeaderInterceptorAspect {
  @Value("${jwt.secret}")
  private String secretKey;
  private JwtUtility jwtUtility = new JwtUtility();

  @Around("@annotation(AuthGuard)")
  public Object checkAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
    HttpServletRequest request = getCurrentHttpRequest();
    String token = extractToken(request);
    validateToken(token);
    Object[] args = modifyArguments(joinPoint.getArgs(), token);
    return joinPoint.proceed(args);
  }

  private HttpServletRequest getCurrentHttpRequest() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    if (attributes == null) {
      throw new InternalServerException("Request context not found.");
    }
    return attributes.getRequest();
  }

  private String extractToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    if (token == null) {
      throw new UnauthorizedException("Authorization 헤더를 찾을 수 없습니다.");
    }
    if (!token.startsWith("Bearer")) {
      throw new UnauthorizedException("Bearer 토큰을 찾을 수 없습니다.");
    }
    return token.substring(7);
  }

  private void validateToken(String token) {
    boolean isValid = jwtUtility.validateToken(secretKey, token);
    if (!isValid) {
      throw new UnauthorizedException("유효하지 않은 토큰입니다.");
    }
  }

  private Object[] modifyArguments(Object[] args, String token) {
    if (args.length > 0 && args[0] instanceof String) {
      args[0] = token;
    }
    return args;
  }
}
