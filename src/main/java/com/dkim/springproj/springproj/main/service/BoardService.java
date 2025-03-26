package com.dkim.springproj.springproj.main.service;

import com.dkim.springproj.springproj.main.dto.PostBodyDto;
import com.dkim.springproj.springproj.main.dto.PostPreviewDto;
import com.dkim.springproj.springproj.main.dto.PostViewDto;
import com.dkim.springproj.springproj.main.entity.Post;
import com.dkim.springproj.springproj.main.entity.User;
import com.dkim.springproj.springproj.main.exception.NotFoundException;
import com.dkim.springproj.springproj.main.exception.UnauthorizedException;
import com.dkim.springproj.springproj.main.repository.PostRepository;
import com.dkim.springproj.springproj.main.repository.UserRepository;
import com.dkim.springproj.springproj.main.utility.JwtUtility;
import com.dkim.springproj.springproj.main.utility.Pagination;
import com.dkim.springproj.springproj.main.utility.Utility;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final JwtUtility jwtUtility;
  private final Utility utility;

  @Value("${jwt.secret}")
  private String secret;

  private static final int PAGE_SIZE = 10;

  public BoardService(PostRepository postRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.jwtUtility = new JwtUtility();
    this.utility = new Utility();
  }

  public Post createPost(String token, PostBodyDto postBodyDto) {
    User user = getUserFromToken(token);
    Post newPost = mapToPost(postBodyDto, user);
    return postRepository.save(newPost);
  }

  public Pagination<PostPreviewDto> getPaginatedPosts(int page) {
    List<PostPreviewDto> posts = postRepository.findAll().stream()
        .map(this::mapToPreviewPostDto)
        .collect(Collectors.toList());
    Pagination<PostPreviewDto> pagination = new Pagination<>(posts, PAGE_SIZE);
    pagination.setCurrentPage(page);
    return pagination;
  }

  public PostViewDto getPostById(Long id) {
    Post post = findPostById(id);
    return mapToViewPostDto(post);
  }

  public void deletePost(String token, Long id) {
    User user = getUserFromToken(token);
    Post post = findPostById(id);
    validatePostOwnership(user, post);
    postRepository.deleteById(id);
  }

  private User getUserFromToken(String token) {
    Claims jwtPayload = jwtUtility.decodeToken(secret, token);
    String userId = jwtPayload.getAudience();
    return userRepository.findByUserId(userId)
        .orElseThrow(() -> new NotFoundException("유효한 사용자를 찾을 수 없습니다."));
  }

  private Post findPostById(Long id) {
    return postRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("게시글을 찾을 수 없습니다."));
  }

  private void validatePostOwnership(User user, Post post) {
    if (!post.getUser().getUserId().equals(user.getUserId())) {
      throw new UnauthorizedException("해당 게시글을 삭제할 권한이 없습니다.");
    }
  }

  private Post mapToPost(PostBodyDto postBodyDto, User user) {
    Post post = new Post();
    post.setTitle(postBodyDto.getTitle());
    post.setPreview(utility.convertPreview(postBodyDto.getContent()));
    post.setContent(postBodyDto.getContent());
    post.setUser(user);
    return post;
  }

  private PostViewDto mapToViewPostDto(Post post) {
    return new PostViewDto(
        post.getId(),
        post.getTitle(),
        post.getContent(),
        post.getUser().getUserId(),
        post.getTimestamp());
  }

  private PostPreviewDto mapToPreviewPostDto(Post post) {
    return new PostPreviewDto(
        post.getId(),
        post.getTitle(),
        post.getPreview(),
        post.getUser().getUserId(),
        post.getTimestamp());
  }
}