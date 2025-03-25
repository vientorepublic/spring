package com.dkim.springproj.springproj.main.repository;

import com.dkim.springproj.springproj.main.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
  // Additional query methods (if needed) can be defined here
}
