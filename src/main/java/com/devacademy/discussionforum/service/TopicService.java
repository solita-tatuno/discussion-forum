package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.*;
import com.devacademy.discussionforum.exception.ResourceNotFoundException;
import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.devacademy.discussionforum.security.TokenService;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    private final TokenService tokenService;

    public TopicService(TopicRepository topicRepository, TokenService tokenService) {
        this.topicRepository = topicRepository;
        this.tokenService = tokenService;
    }

    public Topics addTopic(AddTopic topic, Authentication authentication) {
        Integer userId = tokenService.extractUserIdFromAuthentication(authentication);
        return topicRepository.save(topic.withUserId(userId));
    }

    public List<TopicWithUser> getAll() {
        return topicRepository.findAll();
    }

    public SingleTopic findOne(Integer id) {
        Optional<SingleTopic> topic = topicRepository.findOne(id);
        return topic.orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    public void deleteOne(Integer id) {
        int deletedTopicRowsCount = topicRepository.deleteOne(id);
        if (deletedTopicRowsCount == 0) {
            throw new ResourceNotFoundException("Topic not found");
        }
    }

    public Topics updateTopic(Integer id, AddTopic topic) {
        return topicRepository.update(id, topic)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }
}