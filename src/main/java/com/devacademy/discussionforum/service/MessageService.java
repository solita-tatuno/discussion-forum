package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.AddMessage;
import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.devacademy.discussionforum.security.TokenService;
import com.jooq.discussionforum.tables.pojos.Messages;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final TokenService tokenService;

    public MessageService(MessageRepository messageRepository, TokenService tokenService) {
        this.messageRepository = messageRepository;
        this.tokenService = tokenService;
    }

    public Messages addMessage(AddMessage message, Authentication authentication) {
        Integer userId = tokenService.extractUserIdFromAuthentication(authentication);
        return messageRepository.save(message.withUserId(userId));
    }
}
