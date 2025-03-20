package com.dkim.springproj.springproj.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.dto.LoginBodyDto;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public MessageDto mockLogin(@RequestBody @Valid LoginBodyDto body) {
    return userService.mockLogin(body);
  }

  @PostMapping("/register")
  public MessageDto addUser(@RequestBody @Valid User body) {
    return userService.createUser(body);
  }

  @GetMapping("/findUser")
  public User getUserByID(@RequestParam String id) {
    return userService.getUserByID(id);
  }
}
