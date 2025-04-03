# Spring

스프링 프레임워크 기반 REST API 구현 예시

# Requirements

- Java 21
- MySQL or MariaDB

# Features

- JWT 토큰 기반 사용자 인증
- 기본적인 CRUD 쿼리를 사용하는 게시글 작성, 조회, 수정, 삭제

# Properties

- JWT 서명 키 생성: `openssl rand -hex 64`

```
spring.application.name=springproj
spring.datasource.url=jdbc:mariadb://(Database Host)/(Database Name)
spring.datasource.username=(Database User)
spring.datasource.password=(Database Password)
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
# spring.jpa.properties.hibernate.jdbc.time_zone=Asia/Seoul
# spring.jpa.show-sql=true
jwt.secret=(JWT Secret)
```
