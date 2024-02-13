package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.SingleTopic;
import com.devacademy.discussionforum.dto.TopicWithUser;
import com.devacademy.discussionforum.service.TopicService;
import com.jooq.discussionforum.tables.pojos.Topics;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<SingleTopic> findOne(@PathVariable("id") int id) {
        SingleTopic topic = topicService.findOne(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }
}