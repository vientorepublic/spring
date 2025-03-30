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
public class PostPreviewDto {
  private Long id;
  private String title;
  private String preview;
  private String userId;
  private Date timestamp;
  private Date updated;
}
