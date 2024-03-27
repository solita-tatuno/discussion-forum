package com.devacademy.discussionforum.dto;

import java.time.OffsetDateTime;

public record TopicDTO(
        int id,
        String name,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt,
        UserDTO user
) {
}
