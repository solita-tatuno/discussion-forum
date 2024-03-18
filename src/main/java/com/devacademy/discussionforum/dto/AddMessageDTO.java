package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddMessageDTO(
        @NotNull
        Integer topicId,
        @NotNull
        @NotBlank
        String message,
        @NotNull
        Integer upVotes
) {
}