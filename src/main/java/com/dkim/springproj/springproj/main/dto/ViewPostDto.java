package com.dkim.springproj.springproj.main.dto;

import java.util.Date;

public class ViewPostDto extends PostBodyDto {
  private Long id;
  private String author;
  private Date timestamp;

  public ViewPostDto(Long id, String title, String content, String author, Date timestamp) {
    super(title, content);
    this.id = id;
    this.author = author;
    this.timestamp = timestamp;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }
}
