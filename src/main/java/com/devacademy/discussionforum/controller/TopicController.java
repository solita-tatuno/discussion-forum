package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.service.TopicService;
import com.jooq.discussionforum.tables.pojos.Topics;
import com.jooq.discussionforum.tables.records.TopicsRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
