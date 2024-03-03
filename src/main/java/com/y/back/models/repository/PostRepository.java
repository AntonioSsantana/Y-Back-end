package com.y.back.models.repository;

import com.y.back.models.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Post repository.
 */
public interface PostRepository extends JpaRepository<Post, Integer>{
}
