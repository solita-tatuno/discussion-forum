package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequest(
        @NotNull
        Integer userId,
        @NotNull
        @NotBlank
        String name) {
}
