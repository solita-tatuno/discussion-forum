package com.devacademy.discussionforum.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddMessage(
        @NotNull
        Integer topicId,
        @NotNull
        @NotBlank
        String message,
        @NotNull
        Integer upVotes,
        Integer userId
) {

    public AddMessage withUserId(Integer userId) {
        return new AddMessage(topicId, message, upVotes, userId);
    }
}