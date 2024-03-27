package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddUserDTO(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String password) {
}
