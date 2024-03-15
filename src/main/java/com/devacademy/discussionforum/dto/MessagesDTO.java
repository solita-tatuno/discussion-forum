package com.devacademy.discussionforum.dto;

import java.util.List;

public record MessagesDTO(
        List<MessageWithUser> messages,
        int totalCount
) {
}
