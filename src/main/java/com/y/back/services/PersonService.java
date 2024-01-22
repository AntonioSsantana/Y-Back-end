package com.y.back.services;

import com.y.back.models.entity.Person;
import com.y.back.models.repository.PersonRepository;
import com.y.back.errors.NoRegisteredUsers;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PersonService {
  private PersonRepository personRepository;

  @Autowired
  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }
  
  public Person createPerson(Person person) {
    return personRepository.save(person);
  }

  public List<Person> getAllPersons() {
    if(personRepository.findAll().isEmpty()) {
      throw new NoRegisteredUsers();
    };

    return personRepository.findAll();
  }
}
