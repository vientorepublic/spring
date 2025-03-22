package com.dkim.springproj.springproj.main.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.dkim.springproj.springproj.main.annotation.AuthGuard;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import io.jsonwebtoken.Claims;

@Service
public class MainService {
  @Value("${jwt.secret}")
  private String secretKey;
  private JwtUtility jwtUtility = new JwtUtility();

  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }

  @AuthGuard(role = "ADMIN")
  public MessageDto Auth(String token) {
    Claims claims = jwtUtility.decodeToken(secretKey, token);
    String userId = claims.getAudience();
    return new MessageDto("Hello, " + userId + "!");
  }
}
