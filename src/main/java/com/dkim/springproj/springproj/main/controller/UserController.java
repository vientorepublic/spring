package com.dkim.springproj.springproj.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/addUser")
  public User addUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @GetMapping("/findUser")
  public User getUserByName(@RequestParam String name) {
    return userService.getUserByName(name);
  }
}
