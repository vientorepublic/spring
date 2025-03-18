package com.dkim.springproj.springproj.main.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.exception.BadRequestException;
import com.dkim.springproj.springproj.main.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User getUserByName(String name) {
    if (name.length() == 0) {
      throw new BadRequestException("필수 인자가 비어있습니다.");
    }
    return userRepository.findByName(name);
  }
}
