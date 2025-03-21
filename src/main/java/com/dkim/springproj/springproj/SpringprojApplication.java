package com.dkim.springproj.springproj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringprojApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringprojApplication.class, args);
	}
}
