package com.dkim.springproj.springproj.main.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.dkim.springproj.springproj.main.dto.LoginBodyDto;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.exception.BadRequestException;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.repository.UserRepository;
import com.dkim.springproj.springproj.main.utility.BCrypt;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final BCrypt bcrypt;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.bcrypt = new BCrypt();
  }

  public User createUser(User user) {
    String id = user.getUserId();
    String email = user.getEmail();
    User checkId = userRepository.findByUserId(id);
    if (checkId != null) {
      throw new BadRequestException("아이디가 이미 사용중입니다.");
    }
    User checkEmail = userRepository.findByEmail(email);
    if (checkEmail != null) {
      throw new BadRequestException("이메일 주소가 이미 사용중입니다.");
    }
    String plainPassword = user.getPassword();
    String hash = bcrypt.hash(plainPassword);
    user.setPassword(hash);
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public MessageDto mockLogin(LoginBodyDto body) {
    String id = body.getId();
    String password = body.getPassword();
    User user = userRepository.findByUserId(id);
    if (user == null) {
      throw new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다.");
    }
    String hash = user.getPassword();
    boolean compare = bcrypt.compare(password, hash);
    if (!compare) {
      throw new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다.");
    }
    return new MessageDto("로그인 성공: " + user.getUserId());
  }

  public User getUserByID(String id) {
    if (id.length() == 0) {
      throw new BadRequestException("필수 인자가 비어있습니다.");
    }
    User result = userRepository.findByUserId(id);
    if (result == null) {
      throw new NotFoundException("해당 사용자를 찾을 수 없습니다.");
    }
    return result;
  }
}
