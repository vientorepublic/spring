package com.dkim.springproj.springproj.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dkim.springproj.springproj.main.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUserId(String userId);
}
