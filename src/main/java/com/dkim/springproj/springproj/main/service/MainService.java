package com.dkim.springproj.springproj.main.service;

import org.springframework.stereotype.Service;
import com.dkim.springproj.springproj.main.dto.MessageDto;

@Service
public class MainService {
  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }
}