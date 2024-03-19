package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.AddMessageDTO;
import com.devacademy.discussionforum.dto.MessageUpdateDTO;
import com.devacademy.discussionforum.exception.ResourceNotFoundException;
import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.devacademy.discussionforum.jooq.tables.pojos.Messages;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final TokenService tokenService;

    public MessageService(MessageRepository messageRepository, TokenService tokenService) {
        this.messageRepository = messageRepository;
        this.tokenService = tokenService;
    }

    public Messages addMessage(AddMessageDTO message, Authentication authentication) {
        Integer userId = tokenService.extractUserIdFromAuthentication(authentication);
        return messageRepository.create(userId, message);
    }

    public Messages updateMessage(Integer messageId, MessageUpdateDTO message, Authentication authentication) {
        Integer userId = tokenService.extractUserIdFromAuthentication(authentication);

        if (!Objects.equals(userId, message.userId())) {
            throw new InsufficientAuthenticationException("You can only update your own messages.");
        }

        return messageRepository.update(messageId, message)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }
}
