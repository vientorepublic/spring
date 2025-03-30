package com.dkim.springproj.springproj.main.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostViewDto extends PostBodyDto {
  private Long id;
  private String title;
  private String content;
  private String author;
  private Date timestamp;
  private Date updated;
}
