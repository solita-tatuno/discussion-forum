package com.devacademy.discussionforum.dto;

import java.time.OffsetDateTime;

public record UserMessageDTO(
        int id,
        int topicId,
        String message,
        int upVotes,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        UserDTO user) {
}
