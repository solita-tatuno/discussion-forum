package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.AddMessage;
import com.devacademy.discussionforum.dto.MessageUpdate;
import com.devacademy.discussionforum.service.MessageService;
import com.jooq.discussionforum.tables.pojos.Messages;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<Messages> addMessage(@RequestBody @Valid AddMessage message, Authentication authentication) {
        Messages insertedTopic = messageService.addMessage(message, authentication);
        return new ResponseEntity<>(insertedTopic, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Messages updateMessage(@PathVariable("id") Integer id, @RequestBody @Valid MessageUpdate message, Authentication authentication) {
        return messageService.updateMessage(id, message, authentication);
    }
}
