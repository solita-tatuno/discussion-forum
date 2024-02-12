package com.devacademy.discussionforum.model;

import java.time.OffsetDateTime;

public record MessageWithUser(int id,
                              int topicId,
                              String message,
                              int upVotes,
                              OffsetDateTime createdAt,
                              OffsetDateTime updatedAt,
                              UsersResponse user) {
}
