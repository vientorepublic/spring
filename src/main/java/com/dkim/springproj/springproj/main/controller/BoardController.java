package com.dkim.springproj.springproj.main.controller;

import com.dkim.springproj.springproj.main.annotation.AuthGuard;
import com.dkim.springproj.springproj.main.annotation.AuthGuard.Role;
import com.dkim.springproj.springproj.main.dto.PostBodyDto;
import com.dkim.springproj.springproj.main.dto.ViewPostDto;
import com.dkim.springproj.springproj.main.entity.Post;
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
  public ResponseEntity<Post> createPost(@AuthGuard(role = Role.ALL) String token,
      @RequestBody @Valid PostBodyDto post) {
    Post createdPost = boardService.createPost(token, post);
    return ResponseEntity.ok(createdPost);
  }

  // Retrieve all posts
  @GetMapping("/all")
  public Pagination<ViewPostDto> getAllPosts(@RequestParam(defaultValue = "1") int page) {
    return boardService.getPaginatedPosts(page);
  }

  // Retrieve a post by ID
  @GetMapping("/{id}")
  public ViewPostDto getPostById(@PathVariable Long id) {
    return boardService.getPostById(id);
  }

  // Delete a post by ID
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deletePost(@AuthGuard(role = Role.ALL) String token, @PathVariable Long id) {
    boardService.deletePost(token, id);
    return ResponseEntity.noContent().build();
  }
}
