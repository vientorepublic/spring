package com.dkim.springproj.springproj.main.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.dkim.springproj.springproj.main.dto.AuthRequestDto;
import com.dkim.springproj.springproj.main.dto.AuthResponseDto;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.exception.BadRequestException;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.repository.UserRepository;
import com.dkim.springproj.springproj.main.utility.BCrypt;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import com.dkim.springproj.springproj.main.utility.Utility;

@Service
public class UserService {

  @Value("${jwt.secret}")
  private String secretKey;

  private final UserRepository userRepository;
  private final Utility utility;
  private final BCrypt bcrypt;
  private final JwtUtility jwtUtility;

  public UserService(UserRepository userRepository, Utility utility, BCrypt bcrypt, JwtUtility jwtUtility) {
    this.userRepository = userRepository;
    this.utility = utility;
    this.bcrypt = bcrypt;
    this.jwtUtility = jwtUtility;
  }

  public MessageDto createUser(User user) {
    validateUserInput(user);
    checkUserExistence(user);

    String hashedPassword = bcrypt.hash(user.getPassword());
    user.setPassword(hashedPassword);
    userRepository.save(user);

    return new MessageDto("회원가입이 완료되었습니다.");
  }

  public AuthResponseDto login(AuthRequestDto body) {
    User user = userRepository.findByUserId(body.getUserId())
        .orElseThrow(() -> new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다."));

    if (!bcrypt.compare(body.getPassword(), user.getPassword())) {
      throw new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다.");
    }

    String token = jwtUtility.sign(secretKey, user.getUserId());
    String message = String.format("환영합니다, %s님!", user.getUserId());

    return new AuthResponseDto(message, token);
  }

  public User getUserById(String id) {
    return userRepository.findByUserId(id)
        .orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  private void validateUserInput(User user) {
    if (!utility.isUserId(user.getUserId())) {
      throw new BadRequestException("아이디는 3~25자의 영문 대소문자와 숫자로 이루어져야 합니다.");
    }
    if (!utility.isEmail(user.getEmail())) {
      throw new BadRequestException("올바른 이메일 주소를 입력해주세요.");
    }
    if (!utility.isPassword(user.getPassword())) {
      throw new BadRequestException("비밀번호는 5~30자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.");
    }
  }

  private void checkUserExistence(User user) {
    if (userRepository.existsByUserId(user.getUserId())) {
      throw new BadRequestException("아이디가 이미 사용중입니다.");
    }
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new BadRequestException("이메일 주소가 이미 사용중입니다.");
    }
  }
}
