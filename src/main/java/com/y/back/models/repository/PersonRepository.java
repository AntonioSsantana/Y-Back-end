package com.y.back.models.repository;

import com.y.back.models.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Person repository.
 */
public interface PersonRepository extends JpaRepository<Person, Integer> {
  UserDetails findByUsername(String username);
}
