package com.dkim.springproj.springproj.main.service;

import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.exception.InvalidParamException;

public class MainService {
  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }

  public MessageDto TestRequestParam(String name) {
    if (name.length() == 0) {
      throw new InvalidParamException("필수 인자가 비어있습니다.");
    }
    return new MessageDto("Hello, " + name + "!");
  }
}
