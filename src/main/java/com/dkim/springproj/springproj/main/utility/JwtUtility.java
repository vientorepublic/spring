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
  public String sign(String secret, String userId) {
    String base64SecretKey = Base64.getEncoder().encodeToString(secret.getBytes());
    Date issuedAt = Date.from(Instant.now());
    Date expiredAt = Date.from(Instant.now().plus(Duration.ofDays(1L)));
    @SuppressWarnings("deprecation")
    String token = Jwts.builder()
        .setSubject("Authentication")
        .setIssuer("TestApplication")
        .setAudience(userId)
        .setIssuedAt(issuedAt)
        .setExpiration(expiredAt)
        .signWith(SignatureAlgorithm.HS256, base64SecretKey)
        .compact();
    return token;
  }

  public Claims decodeToken(String secret, String token) {
    // if (!token.startsWith("Bearer")) {
    // return false;
    // }
    byte[] decodedSecret = Base64.getDecoder().decode(secret);
    JwtParser jwtSubject = Jwts.parserBuilder()
        .setSigningKey(decodedSecret)
        .build();
    return jwtSubject.parseClaimsJws(token).getBody();
  }
}
