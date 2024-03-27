package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.AddMessageDTO;
import com.devacademy.discussionforum.dto.MessageUpdateDTO;
import com.devacademy.discussionforum.service.MessageService;
import com.devacademy.discussionforum.jooq.tables.pojos.Message;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@RequestBody @Valid AddMessageDTO message, Authentication authentication) {
        return messageService.addMessage(message, authentication);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Message updateMessage(@PathVariable("id") Integer id, @RequestBody @Valid MessageUpdateDTO message, Authentication authentication) {
        return messageService.updateMessage(id, message, authentication);
    }
}
