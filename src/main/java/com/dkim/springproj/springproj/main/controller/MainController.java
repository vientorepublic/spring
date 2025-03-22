package com.dkim.springproj.springproj.main.controller;

import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class MainController {
  @Autowired
  private MainService mainService;

  @GetMapping
  public MessageDto Main() {
    return mainService.Main();
  }

  @GetMapping("/auth")
  public MessageDto Auth() {
    return mainService.Auth("");
  }
}
