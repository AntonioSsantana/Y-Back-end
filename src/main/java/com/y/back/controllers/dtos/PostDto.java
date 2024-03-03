package com.y.back.controllers.dtos;

import java.time.LocalDate;

public record PostDto(Integer id, String message, LocalDate createdDate) {
}
