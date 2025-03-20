package com.dkim.springproj.springproj.main.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
  public String hash(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
  }

  public boolean compare(String password, String hash) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(password, hash);
  }
}