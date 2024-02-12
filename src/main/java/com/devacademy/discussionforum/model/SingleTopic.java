package com.devacademy.discussionforum.model;

import com.jooq.discussionforum.tables.pojos.Messages;

import java.time.OffsetDateTime;

public record SingleTopic(int id, String name, OffsetDateTime createdAt, OffsetDateTime updatedAt,
                          UsersResponse user, Messages[] messages) {
}
