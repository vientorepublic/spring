package com.dkim.springproj.springproj.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.AuthRequestDto;
import com.dkim.springproj.springproj.main.dto.AuthResponseDto;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private UserService userService;

  @PostMapping("/login")
  public AuthResponseDto Login(@RequestBody @Valid AuthRequestDto body) {
    return userService.login(body);
  }

  @PostMapping("/register")
  public MessageDto addUser(@RequestBody @Valid User body) {
    return userService.createUser(body);
  }

  @GetMapping("/findUser")
  public User getUserByID(@RequestParam String id) {
    return userService.getUserById(id);
  }
}
