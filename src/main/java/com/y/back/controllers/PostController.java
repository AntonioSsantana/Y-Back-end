package com.y.back.controllers;

import com.y.back.controllers.dtos.PostDto;
import com.y.back.controllers.dtos.ResponseDto;
import com.y.back.models.entity.Post;
import com.y.back.services.PostService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Post controller.
 */
@RestController
@RequestMapping("/posts")
public class PostController {
  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  /**
   * Return all posts or a not found exception.
   * All posts or exception @return
   */
  @GetMapping("")
  public ResponseEntity<ResponseDto<List<PostDto>>> getAllPosts() {
    try {
      List<Post> posts = postService.getAllPosts();
      List<PostDto> ps = posts.stream().map((post) -> new PostDto(post.getId(),
          post.getMessage(), post.getCreatedDate(), post.getCreatedTime(), post.getPerson()))
          .collect(Collectors.toList());

      ResponseDto<List<PostDto>> res = new ResponseDto<List<PostDto>>("Founded!", ps);

      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      ResponseDto<List<PostDto>> res = new ResponseDto<List<PostDto>>(e.getMessage(), null);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
  }

  @PostMapping("/{username}/upload")
  public ResponseEntity<ResponseDto<PostDto>> createPost(@RequestBody Post post, @PathVariable String username) {
    try {
      Post ps = postService.createPost(post, username);
      PostDto postDto = new PostDto(ps.getId(), ps.getMessage(), ps.getCreatedDate(),
          ps.getCreatedTime(), ps.getPerson());
      ResponseDto<PostDto> res = new ResponseDto<PostDto>("Post uploaded!", postDto);

      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      ResponseDto<PostDto> res = new ResponseDto<PostDto>(e.getMessage(), null);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
  }

  @GetMapping("/{username}")
  public ResponseEntity<ResponseDto<List<PostDto>>> getPostsByUsername(@PathVariable String username) {
    try {
      List<Post> posts = postService.getPostsByUsername(username);

      List<PostDto> postsDto = posts.stream().map((post) -> new PostDto(post.getId(), 
        post.getMessage(), post.getCreatedDate(), post.getCreatedTime(), post.getPerson())).collect(Collectors.toList()); 
      
      ResponseDto<List<PostDto>> res = new ResponseDto<List<PostDto>>("Founded sucessfully", postsDto);

      return ResponseEntity.status(HttpStatus.OK).body(res);
    } catch (Exception e) {
      ResponseDto<List<PostDto>> res = new ResponseDto<List<PostDto>>(e.getMessage(), null);

      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
  }
}
