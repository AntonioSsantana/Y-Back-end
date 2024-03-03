package com.y.back.controllers.dtos;

import com.y.back.models.entity.Person;
import java.time.LocalDate;

public record PostDto(Integer id, String message, LocalDate createdDate, Person person) {
}
