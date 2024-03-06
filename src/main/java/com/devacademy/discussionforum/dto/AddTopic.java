package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddTopic(
        @NotNull
        @NotBlank
        String name,

        Integer userId
) {
    public AddTopic withUserId(Integer userId) {
        return new AddTopic(name, userId);
    }
}
