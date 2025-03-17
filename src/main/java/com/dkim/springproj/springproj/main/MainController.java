package com.dkim.springproj.springproj.main;

import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MainController {
  @GetMapping("/")
  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }

  @GetMapping("/test")
  public MessageDto Test(@RequestParam String name) {
    return new MessageDto("Hello, " + name + "!");
  }
}
