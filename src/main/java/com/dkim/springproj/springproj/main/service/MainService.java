package com.dkim.springproj.springproj.main.service;

import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.exception.EmptyParamException;

public class MainService {
  public MessageDto Main() {
    return new MessageDto("Hello, World!");
  }

  public MessageDto TestRequestParam(String name) {
    if (name.length() == 0) {
      throw new EmptyParamException();
    }
    return new MessageDto("Hello, " + name + "!");
  }
}
