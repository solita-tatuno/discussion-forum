package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.AddTopic;
import com.devacademy.discussionforum.dto.SingleTopic;
import com.devacademy.discussionforum.dto.TopicWithUser;
import com.devacademy.discussionforum.service.TopicService;
import com.jooq.discussionforum.tables.pojos.Topics;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<Topics> addTopic(@RequestBody @Valid AddTopic topic, Authentication authentication) {
        Topics insertedTopic = topicService.addTopic(topic, authentication);
        return new ResponseEntity<>(insertedTopic, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TopicWithUser>> getAll() {
        List<TopicWithUser> allTopics = topicService.getAll();
        return new ResponseEntity<>(allTopics, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SingleTopic> findOne(@PathVariable("id") Integer id) {
        SingleTopic topic = topicService.findOne(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @PreAuthorize("hasRole(T(com.devacademy.discussionforum.security.UserRole).ROLE_ADMIN)")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTopic(@PathVariable("id") Integer id) {
        topicService.deleteOne(id);
    }


    @PreAuthorize("hasRole(T(com.devacademy.discussionforum.security.UserRole).ROLE_ADMIN)")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Topics updateTopic(@PathVariable("id") Integer id, @RequestBody @Valid AddTopic topic) {
        return topicService.updateTopic(id, topic);
    }
}