package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.*;
import com.devacademy.discussionforum.service.TopicService;
import com.devacademy.discussionforum.jooq.tables.pojos.Topic;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topic addTopic(@RequestBody @Valid TopicDataDTO topic, Authentication authentication) {
        return topicService.addTopic(topic, authentication);
    }

    @GetMapping
    public TopicsDTO getAll(Pageable pageable) {
        return topicService.getAll(pageable);
    }

    @GetMapping("/{id}/messages")
    public MessagesDTO getMessages(@PathVariable("id") Integer id, Pageable pageable) {
        return topicService.getTopicMessages(id, pageable);
    }

    @GetMapping("/{id}")
    public TopicDTO findOne(@PathVariable("id") Integer id) {
        return topicService.findOne(id);
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
    public Topic updateTopic(@PathVariable("id") Integer id, @RequestBody @Valid TopicDataDTO topic) {
        return topicService.updateTopic(id, topic);
    }
}