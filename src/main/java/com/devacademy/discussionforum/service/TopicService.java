package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.model.SingleTopic;
import com.devacademy.discussionforum.model.TopicWithUser;
import com.devacademy.discussionforum.repostitory.TopicRepository;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Topics addTopic(Topics topic) {
        return topicRepository.save(topic);
    }

    public List<TopicWithUser> getAll() {
        return topicRepository.findAll();
    }

    public SingleTopic findOne(int id) {
        return topicRepository.findOne(id);
    }
}