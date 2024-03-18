package com.devacademy.discussionforum.dto;

import java.util.List;

public record TopicsDTO(int totalCount, List<UserTopicDTO> topics) {
}
