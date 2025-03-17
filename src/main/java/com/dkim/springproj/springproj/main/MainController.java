package com.dkim.springproj.springproj.main;

import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.MessageDto;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {
  @GetMapping("/")
  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }
}
