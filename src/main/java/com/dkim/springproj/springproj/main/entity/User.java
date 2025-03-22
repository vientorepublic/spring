package com.dkim.springproj.springproj.main.entity;

import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false, updatable = false)
  private Long id;

  @Column(nullable = false)
  @NotNull
  @NotBlank
  private String userId;

  @Column(nullable = false)
  @NotNull
  @NotBlank
  private String email;

  @Column(nullable = false)
  @NotNull
  @NotBlank
  private String password;

  @Column(nullable = false)
  @NotNull
  @NotBlank
  private String role = "USER";

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date timestamp;

  public Long getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Date getCreationTimestamp() {
    return timestamp;
  }
}
