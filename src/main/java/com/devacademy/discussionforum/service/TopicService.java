package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Topics addTopic(Topics topic) {
        return topicRepository.save(topic);
    }
}
