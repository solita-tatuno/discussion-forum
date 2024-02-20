package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String password) {
}
