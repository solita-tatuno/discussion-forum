package com.devacademy.discussionforum.dto;

import java.time.OffsetDateTime;

public record SingleTopic(int id,
                          String name,
                          OffsetDateTime createdAt,
                          OffsetDateTime updatedAt,
                          UsersResponse user,
                          MessageWithUser[] messages) {
}
