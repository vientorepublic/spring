package com.dkim.springproj.springproj.main.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.exception.InternalServerException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.repository.UserRepository;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HeaderInterceptorAspect {
  @Value("${jwt.secret}")
  private String secretKey;

  private final UserRepository userRepository;
  private final JwtUtility jwtUtility;

  public HeaderInterceptorAspect(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.jwtUtility = new JwtUtility();
  }

  @Around("@annotation(authGuard)")
  public Object checkAuthorization(ProceedingJoinPoint joinPoint, AuthGuard authGuard) throws Throwable {
    HttpServletRequest request = getCurrentHttpRequest();
    String token = extractToken(request);
    Claims claims = decodeAndValidateToken(token);

    validateUserAndRole(claims, authGuard);

    Object[] modifiedArgs = modifyArguments(joinPoint.getArgs(), token);
    return joinPoint.proceed(modifiedArgs);
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
    if (token == null || !token.startsWith("Bearer ")) {
      throw new UnauthorizedException("유효한 Authorization 헤더를 찾을 수 없습니다.");
    }
    return token.substring(7);
  }

  private Claims decodeAndValidateToken(String token) {
    try {
      return jwtUtility.decodeToken(secretKey, token);
    } catch (Exception e) {
      throw new UnauthorizedException("유효하지 않은 토큰입니다.");
    }
  }

  private void validateUserAndRole(Claims claims, AuthGuard authGuard) {
    String userId = claims.getAudience();
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UnauthorizedException("유효하지 않은 사용자입니다."));
    String role = authGuard.role();
    String userRole = user.getRole();
    if (role.equals("ALL")) {
      return;
    }
    if (!userRole.equals(role)) {
      throw new UnauthorizedException("권한이 없습니다.");
    }
  }

  private Object[] modifyArguments(Object[] args, String token) {
    if (args.length > 0 && args[0] instanceof String) {
      args[0] = token;
    }
    return args;
  }
}
