package com.dkim.springproj.springproj.main.controller;

import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.service.MainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class MainController {
  private MainService mainService;

  private MainController() {
    this.mainService = new MainService();
  }

  @GetMapping("/")
  public MessageDto Main() {
    return this.mainService.Main();
  }

  @GetMapping("/test")
  public MessageDto TestRequestParam(@RequestParam String name) {
    return this.mainService.TestRequestParam(name);
  }
}
