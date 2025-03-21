package com.dkim.springproj.springproj.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.dkim.springproj.springproj.main.annotation.HeaderInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  private final HeaderInterceptor headerInterceptor;

  public WebConfig(HeaderInterceptor headerInterceptor) {
    this.headerInterceptor = headerInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(headerInterceptor)
        .addPathPatterns("/**");
  }
}
