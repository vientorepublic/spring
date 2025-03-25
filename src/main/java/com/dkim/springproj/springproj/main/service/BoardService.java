package com.dkim.springproj.springproj.main.service;

import com.dkim.springproj.springproj.main.dto.PostBodyDto;
import com.dkim.springproj.springproj.main.entity.Post;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.repository.PostRepository;
import com.dkim.springproj.springproj.main.repository.UserRepository;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import io.jsonwebtoken.Claims;

@Service
public class BoardService {
  private final PostRepository postRepository;
  private final UserRepository userRepository;
  @Value("${jwt.secret}")
  private String secret;
  private final JwtUtility jwtUtility;

  public BoardService(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.jwtUtility = new JwtUtility();
  }

  // Create a new post
  public Post createPost(String token, PostBodyDto post) {
    Claims jwtPayload = jwtUtility.decodeToken(secret, token);
    String userId = jwtPayload.getAudience();
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new NotFoundException("유효한 사용자를 찾을 수 없습니다."));
    Post newPost = new Post();
    newPost.setTitle(post.getTitle());
    newPost.setContent(post.getContent());
    newPost.setUser(user);
    return postRepository.save(newPost);
  }

  // Retrieve all posts
  public List<Post> getAllPosts() {
    return postRepository.findAll();
  }

  // Retrieve a post by ID
  public Optional<Post> getPostById(Long id) {
    return postRepository.findById(id);
  }

  // Delete a post by ID
  public void deletePost(String token, Long id) {
    Claims jwtPayload = jwtUtility.decodeToken(secret, token);
    String userId = jwtPayload.getAudience();
    User user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new NotFoundException("유효한 사용자를 찾을 수 없습니다."));
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
    if (!post.getUser().getUserId().equals(user.getUserId())) {
      throw new UnauthorizedException("해당 게시글을 삭제할 권한이 없습니다.");
    }
    postRepository.deleteById(id);
  }
}
