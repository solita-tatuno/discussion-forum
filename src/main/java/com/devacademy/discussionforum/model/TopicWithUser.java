package com.devacademy.discussionforum.model;

import java.time.OffsetDateTime;

public record TopicWithUser(int id, String name, OffsetDateTime createdAt, OffsetDateTime updatedAt,
                            UsersResponse user) {
}

