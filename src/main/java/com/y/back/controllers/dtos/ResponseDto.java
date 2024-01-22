package com.y.back.controllers.dtos;

public record ResponseDto<T>(String message, T data) {
}