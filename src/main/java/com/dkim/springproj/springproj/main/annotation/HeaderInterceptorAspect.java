package com.dkim.springproj.springproj.main.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
import java.lang.reflect.Parameter;

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

  @Around("@annotation(org.aspectj.lang.annotation.Pointcut) || execution(* *(.., @com.dkim.springproj.springproj.main.annotation.AuthGuard (*), ..))")
  public Object checkAuthorization(ProceedingJoinPoint joinPoint) throws Throwable {
    HttpServletRequest request = getCurrentHttpRequest();
    String token = extractToken(request);
    Claims claims = decodeAndValidateToken(token);

    AuthGuard authGuard = getAuthGuardAnnotation(joinPoint);
    if (authGuard != null) {
      validateUserAndRole(claims, authGuard);
    }

    Object[] modifiedArgs = injectTokenIntoParameters(joinPoint, token);
    return joinPoint.proceed(modifiedArgs);
  }

  private AuthGuard getAuthGuardAnnotation(ProceedingJoinPoint joinPoint) {
    Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();
    for (Parameter parameter : parameters) {
      if (parameter.isAnnotationPresent(AuthGuard.class)) {
        return parameter.getAnnotation(AuthGuard.class);
      }
    }
    return null;
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

  private Object[] injectTokenIntoParameters(ProceedingJoinPoint joinPoint, String token) {
    Object[] args = joinPoint.getArgs();
    Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameters();

    for (int i = 0; i < parameters.length; i++) {
      if (parameters[i].isAnnotationPresent(AuthGuard.class)) {
        args[i] = token;
      }
    }
    return args;
  }
}