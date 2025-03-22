package com.dkim.springproj.springproj.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dkim.springproj.springproj.main.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserId(String userId);

  User findByEmail(String email);

  boolean existsByUserId(String userId);

  boolean existsByEmail(String email);
}
