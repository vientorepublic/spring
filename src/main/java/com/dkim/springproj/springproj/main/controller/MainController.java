package com.dkim.springproj.springproj.main.controller;

import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.service.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/")
public class MainController {
  private final MainService mainService;

  public MainController(MainService mainService) {
    this.mainService = mainService;
  }

  @GetMapping
  public MessageDto Main() {
    return mainService.Main();
  }

  @GetMapping("/test")
  public MessageDto TestRequestParam(@RequestParam String name) {
    return mainService.TestRequestParam(name);
  }
}
