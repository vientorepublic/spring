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
    boolean isValidEmail = utility.isEmail(email);
    if (!isValidEmail) {
      throw new BadRequestException("올바른 이메일 주소를 입력해주세요.");
    }
    boolean isUserIdExist = userRepository.existsByUserId(userId);
    if (isUserIdExist) {
      throw new BadRequestException("아이디가 이미 사용중입니다.");
    }
    boolean isEmailExist = userRepository.existsByEmail(email);
    if (isEmailExist) {
      throw new BadRequestException("이메일 주소가 이미 사용중입니다.");
    }
    String plainPassword = user.getPassword();
    String hash = bcrypt.hash(plainPassword);
    user.setPassword(hash);
    userRepository.save(user);
    return new MessageDto("회원가입이 완료되었습니다.");
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public AuthResponseDto Login(AuthRequestDto body) {
    String userId = body.getUserId();
    String password = body.getPassword();
    User user = userRepository.findByUserId(userId);
    if (user == null) {
      throw new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다.");
    }
    String hash = user.getPassword();
    boolean compare = bcrypt.compare(password, hash);
    if (!compare) {
      throw new UnauthorizedException("아이디 또는 비밀번호가 틀렸습니다.");
    }
    String token = new JwtUtility().sign(secretKey, userId);
    return new AuthResponseDto(String.format("환영합니다, %s님!", userId), token);
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
