package com.dkim.springproj.springproj.main.service;

import org.springframework.stereotype.Service;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.exception.BadRequestException;

@Service
public class MainService {
  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }

  public MessageDto TestRequestParam(String name) {
    if (name.length() == 0) {
      throw new BadRequestException("필수 인자가 비어있습니다.");
    }
    return new MessageDto("Hello, " + name + "!");
  }
}
