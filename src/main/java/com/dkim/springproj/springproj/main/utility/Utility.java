package com.dkim.springproj.springproj.main.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utility {
  public String getISOTimestamp() {
    TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    df.setTimeZone(tz);
    Date now = new Date();
    String timestamp = df.format(now);
    return timestamp;
  }

  public boolean isEmail(String email) {
    return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
  }
}
