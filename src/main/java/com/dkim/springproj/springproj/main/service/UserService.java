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

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.utility = new Utility();
    this.bcrypt = new BCrypt();
  }

  public MessageDto createUser(User user) {
    String userId = user.getUserId();
    String email = user.getEmail();
    String password = user.getPassword();
    boolean isValidUserId = utility.isUserId(userId);
    if (!isValidUserId) {
      throw new BadRequestException("아이디는 3~25자의 영문 대소문자와 숫자로 이루어져야 합니다.");
    }
    boolean isValidEmail = utility.isEmail(email);
    if (!isValidEmail) {
      throw new BadRequestException("올바른 이메일 주소를 입력해주세요.");
    }
    boolean isValidPassword = utility.isPassword(password);
    if (!isValidPassword) {
      throw new BadRequestException("비밀번호는 5~30자의 영문 대소문자, 숫자, 특수문자로 이루어져야 합니다.");
    }
    boolean isUserIdExist = userRepository.existsByUserId(userId);
    if (isUserIdExist) {
      throw new BadRequestException("아이디가 이미 사용중입니다.");
    }
    boolean isEmailExist = userRepository.existsByEmail(email);
    if (isEmailExist) {
      throw new BadRequestException("이메일 주소가 이미 사용중입니다.");
    }
    String hash = bcrypt.hash(password);
    user.setPassword(hash);
    userRepository.save(user);
    return new MessageDto("회원가입이 완료되었습니다.");
  }

  public AuthResponseDto Login(AuthRequestDto body) {
    String userId = body.getUserId();
    String password = body.getPassword();
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다."));
    String hash = user.getPassword();
    boolean compare = bcrypt.compare(password, hash);
    if (!compare) {
      throw new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다.");
    }
    String token = new JwtUtility().sign(secretKey, userId);
    String message = String.format("환영합니다, %s님!", userId);
    return new AuthResponseDto(message, token);
  }

  public User getUserByID(String id) {
    User result = userRepository.findByUserId(id)
        .orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
    return result;
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
