package com.y.back.services;

import com.y.back.errors.NoPostsFound;
import com.y.back.errors.UserNotFoundException;
import com.y.back.models.entity.Person;
import com.y.back.models.entity.Post;
import com.y.back.models.repository.PersonRepository;
import com.y.back.models.repository.PostRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Post service.
 */
@Service
public class PostService {
  private final PostRepository postRepository;
  private final PersonRepository personRepository;

  @Autowired
  public PostService(PostRepository postRepository, PersonRepository personRepository) {
    this.postRepository = postRepository;
    this.personRepository = personRepository;
  }

  /**
   * Method that return a post object and insert the personId in database.
   * Post @param post
   * Person id @param personId
   * Return post object @return
   */
  public Post createPost(Post post, String username) {
    Optional<Person> personById = personRepository.findByUsername(username);

    if(!personById.isPresent()) {
      throw new UserNotFoundException();
    }

    Person person = personById.get();
    post.setPerson(person);
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
