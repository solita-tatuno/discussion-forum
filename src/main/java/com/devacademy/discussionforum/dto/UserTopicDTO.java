package com.devacademy.discussionforum.dto;

import java.time.OffsetDateTime;

public record UserTopicDTO(
        int id,
        String name,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        UserDTO user,
        int messageCount,
        OffsetDateTime lastMessageTime
) {
}

