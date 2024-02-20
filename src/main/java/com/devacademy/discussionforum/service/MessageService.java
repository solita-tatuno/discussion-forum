package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.dto.MessageRequest;
import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.jooq.discussionforum.tables.pojos.Messages;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Messages addMessage(MessageRequest message) {
        return messageRepository.save(message);
    }
}
