package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.SingleTopic;
import com.devacademy.discussionforum.dto.TopicRequest;
import com.devacademy.discussionforum.dto.TopicWithUser;
import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topics addTopic(TopicRequest topic) {
        return topicRepository.save(topic);
    }

    public List<TopicWithUser> getAll() {
        return topicRepository.findAll();
    }

    public SingleTopic findOne(int id) {
        return topicRepository.findOne(id);
    }
}