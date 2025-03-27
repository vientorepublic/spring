package com.dkim.springproj.springproj.main.controller;

import com.dkim.springproj.springproj.main.annotation.AuthGuard;
import com.dkim.springproj.springproj.main.annotation.AuthGuard.Role;
import com.dkim.springproj.springproj.main.dto.MessageDto;
import com.dkim.springproj.springproj.main.dto.PostBodyDto;
import com.dkim.springproj.springproj.main.dto.PostPreviewDto;
import com.dkim.springproj.springproj.main.dto.PostViewDto;
import com.dkim.springproj.springproj.main.service.BoardService;
import com.dkim.springproj.springproj.main.utility.Pagination;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class BoardController {
  @Autowired
  private BoardService boardService;

  // Create a new post
  @PostMapping("/create")
  public MessageDto createPost(@AuthGuard(role = Role.ALL) String token,
      @RequestBody @Valid PostBodyDto post) {
    return boardService.createPost(token, post);
  }

  // Retrieve all posts
  @GetMapping("/all")
  public Pagination<PostPreviewDto> getPaginatedPosts(@RequestParam(defaultValue = "1") int page) {
    return boardService.getPaginatedPosts(page);
  }

  // Retrieve a post by ID
  @GetMapping("/{id}")
  public PostViewDto getPostById(@PathVariable Long id) {
    return boardService.getPostById(id);
  }

  // Update a post by ID
  @PutMapping("/edit/{id}")
  public MessageDto updatePost(@AuthGuard(role = Role.ALL) String token, @PathVariable Long id,
      @RequestBody @Valid PostBodyDto body) {
    return boardService.updatePost(token, id, body);
  }

  // Delete a post by ID
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deletePost(@AuthGuard(role = Role.ALL) String token, @PathVariable Long id) {
    boardService.deletePost(token, id);
    return ResponseEntity.noContent().build();
  }
}
