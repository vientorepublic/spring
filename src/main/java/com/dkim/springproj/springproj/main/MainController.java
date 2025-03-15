package com.dkim.springproj.springproj.main;

import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.ResponseDto;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class MainController {
  @GetMapping("/")
  public ResponseDto Main() {
    return new ResponseDto("hello, world!");
  }
}
