package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicDataDTO(
        @NotNull
        @NotBlank
        String name
) {
}
