package com.y.back.models.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Post entity.
 */
@Entity
@Table(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column( name = "message")
  private String message;

  @Column( name = "created_date")
  private LocalDate createdDate;

  @ManyToOne
  @JoinColumn( name = "person_id")
  private Person person;

  /**
   * Constructor.
   * Post id @param id
   * Post message @param message
   */
  public Post(Integer id, String message, LocalDate createdDate, Person person) {
    this.id = id;
    this.message = message;
    this.createdDate = createdDate;
    this.person = person;
  }

  public Post(){
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
