package com.devacademy.discussionforum.dto;

import java.time.OffsetDateTime;

public record MessageWithUser(int id,
                              int topicId,
                              String message,
                              int upVotes,
                              OffsetDateTime createdAt,
                              OffsetDateTime updatedAt,
                              UserResponse user) {
}
