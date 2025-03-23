package com.dkim.springproj.springproj.main.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthGuard {
  Role role() default Role.USER;

  enum Role {
    ALL, ADMIN, USER
  }
}
