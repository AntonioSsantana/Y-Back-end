package com.y.back.controllers;

import com.y.back.models.entity.Person;
import com.y.back.services.PersonService;
import com.y.back.services.TokenService;
import com.y.back.controllers.dtos.AuthDto;
import com.y.back.controllers.dtos.PersonDto;
import com.y.back.controllers.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * Rest Controller. 
 */
@RestController
@RequestMapping("")
public class PersonController {

  private final PersonService personService;
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  @Autowired
  public PersonController(PersonService personService,
      AuthenticationManager authenticationManager, TokenService tokenService) {
    this.personService = personService;
    this.authenticationManager = authenticationManager;
    this.tokenService = tokenService;
  }

  /**
   * Person register method, receive a person object and insert in database.
   * Object @param person
   * Default response @return
   */
  @PostMapping("/register")
  public ResponseEntity<ResponseDto<PersonDto>> registerPerson(@RequestBody Person person) {
    try {
      Person rg = personService.createPerson(person);

      PersonDto resPerson = new PersonDto(rg.getUsername(), rg.getEmail(), rg.getPassword(), rg.getCreatedDate(),
          rg.getCreatedTime(), rg.getRole());

      ResponseDto<PersonDto> res = new ResponseDto<PersonDto>("Person created!", resPerson);

      return ResponseEntity.status(HttpStatus.CREATED).body(res);
    } catch (Exception e) {
      ResponseDto<PersonDto> res = new ResponseDto<PersonDto>(e.getMessage(), null);

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseDto<String>> login(@RequestBody AuthDto authDto) {
    UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
      authDto.username(), authDto.password());

    Authentication auth = authenticationManager.authenticate(usernamePassword);

    Person person = (Person) auth.getPrincipal();
    String token = tokenService.generateToken(person);

    ResponseDto<String> responseDto = new ResponseDto<String>("Authenticated!", token);

    return ResponseEntity.status(HttpStatus.OK).body(responseDto);
  }
}
