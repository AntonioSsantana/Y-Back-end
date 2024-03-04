package com.y.back.models.repository;

import com.y.back.models.entity.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Post repository.
 */
public interface PostRepository extends JpaRepository<Post, Integer>{
  List<Post> findPostsByPersonId(Integer personId);
}
