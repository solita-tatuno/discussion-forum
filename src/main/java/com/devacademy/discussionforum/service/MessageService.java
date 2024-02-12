package com.devacademy.discussionforum.service;

import com.devacademy.discussionforum.repostitory.MessageRepository;
import com.jooq.discussionforum.tables.pojos.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Messages addMessage(Messages message) {
        return messageRepository.save(message);
    }
}
