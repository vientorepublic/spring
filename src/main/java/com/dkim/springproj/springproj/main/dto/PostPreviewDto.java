package com.dkim.springproj.springproj.main.dto;

import java.util.Date;

public class PostPreviewDto {
  private Long id;
  private String title;
  private String preview;
  private String userId;
  private Date timestamp;

  public PostPreviewDto(Long id, String title, String preview, String userId, Date timestamp) {
    this.id = id;
    this.title = title;
    this.preview = preview;
    this.userId = userId;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPreview() {
    return preview;
  }

  public void setPreview(String preview) {
    this.preview = preview;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
