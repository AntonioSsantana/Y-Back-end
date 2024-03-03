package com.y.back.services;

import com.y.back.errors.NoPostsFound;
import com.y.back.models.entity.Post;
import com.y.back.models.repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Post service.
 */
@Service
public class PostService {
  private final PostRepository postRepository;

  @Autowired
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public Post createPost(Post post) {
    return postRepository.save(post);
  }

  /**
   * Return all posts or a exception.
   * Posts object or exception @return
   */
  public List<Post> getAllPosts() {
    if(postRepository.findAll().isEmpty()){
      throw new NoPostsFound();
    }

    return postRepository.findAll();
  }
}
