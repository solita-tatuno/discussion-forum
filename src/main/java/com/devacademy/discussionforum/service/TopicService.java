package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.*;
import com.devacademy.discussionforum.exception.ResourceNotFoundException;
import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final TokenService tokenService;
    private final MessageRepository messageRepository;

    public TopicService(TopicRepository topicRepository, TokenService tokenService, MessageRepository messageRepository) {
        this.topicRepository = topicRepository;
        this.tokenService = tokenService;
        this.messageRepository = messageRepository;
    }

    public Topics addTopic(TopicDataDTO topic, Authentication authentication) {
        Integer userId = tokenService.extractUserIdFromAuthentication(authentication);
        return topicRepository.create(userId, topic);
    }

    public TopicsDTO getAll(Pageable pageable) {
        return topicRepository.getAll(pageable);
    }

    @Transactional
    public void deleteOne(Integer id) {
        messageRepository.deleteTopicMessages(id);
        int deletedTopicRowsCount = topicRepository.deleteOne(id);

        if (deletedTopicRowsCount == 0) {
            throw new ResourceNotFoundException("Topic not found");
        }
    }

    public Topics updateTopic(Integer id, TopicDataDTO topic) {
        return topicRepository.update(id, topic)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    public MessagesDTO getTopicMessages(Integer id, Pageable pageable) {
        return messageRepository.getTopicMessages(id, pageable);
    }
}