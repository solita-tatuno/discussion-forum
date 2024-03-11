package com.devacademy.discussionforum.dto;

import java.time.OffsetDateTime;

public record TopicWithUser(int id,
                            String name,
                            OffsetDateTime createdAt,
                            OffsetDateTime updatedAt,
                            UserResponse user,
                            int messageCount,
                            OffsetDateTime lastMessageTime
) {
}

