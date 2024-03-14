package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MessageUpdate(
        @NotNull
        @NotBlank
        String message,
        @NotNull
        Integer upVotes,
        @NotNull
        Integer userId
) {
}
