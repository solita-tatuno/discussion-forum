package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.model.TopicWithUser;
import com.devacademy.discussionforum.service.TopicService;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @PostMapping
    public ResponseEntity<Topics> addTopic(@RequestBody Topics topic) {
        Topics newTopic = topicService.addTopic(topic);

        return new ResponseEntity<>(newTopic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TopicWithUser>> getAll() {
        List<TopicWithUser> allTopics = topicService.getAll();
        return new ResponseEntity<>(allTopics, HttpStatus.OK);
    }
}