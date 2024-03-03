package com.y.back.controllers.dtos;

import com.y.back.models.entity.Person;
import java.time.LocalDate;
import java.time.LocalTime;

public record PostDto(Integer id, String message, LocalDate createdDate, LocalTime createdTime, Person person) {
}
