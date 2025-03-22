package com.dkim.springproj.springproj.main.utility;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtility {
  private String encodeSecret(String secret) {
    return Base64.getEncoder().encodeToString(secret.getBytes());
  }

  @SuppressWarnings("deprecation")
  public String sign(String secret, String userId) {
    String base64SecretKey = encodeSecret(secret);
    Instant now = Instant.now();
    Date issuedAt = Date.from(now);
    Date expiredAt = Date.from(now.plus(Duration.ofDays(1)));

    return Jwts.builder()
        .setSubject("Authentication")
        .setIssuer("TestApplication")
        .setAudience(userId)
        .setIssuedAt(issuedAt)
        .setExpiration(expiredAt)
        .signWith(SignatureAlgorithm.HS256, base64SecretKey)
        .compact();
  }

  public boolean validateToken(String secret, String token) {
    try {
      getJwtParser(secret).parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Claims decodeToken(String secret, String token) {
    return getJwtParser(secret).parseClaimsJws(token).getBody();
  }

  private JwtParser getJwtParser(String secret) {
    return Jwts.parserBuilder()
        .setSigningKey(secret.getBytes())
        .build();
  }
}
