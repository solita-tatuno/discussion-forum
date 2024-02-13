package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.service.MessageService;
import com.jooq.discussionforum.tables.pojos.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {


    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Messages> addMessage(@RequestBody Messages message) {
        Messages newMessage = messageService.addMessage(message);

        return new ResponseEntity<>(newMessage, HttpStatus.CREATED);
    }
}
