package com.devacademy.discussionforum.controller;

import com.devacademy.discussionforum.dto.TopicDataDTO;
import com.devacademy.discussionforum.exception.ResourceNotFoundException;
import com.devacademy.discussionforum.jooq.tables.pojos.Topic;
import com.devacademy.discussionforum.service.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
@EnableMethodSecurity
public class TopicControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TopicService topicService;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(value = "mockUser")
    @Test
    public void whenPostToTopicsAndValidTopicResponseIsCorrect() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO("New topic");

        Topic createdTopic = new Topic(1, 2, "New topic", OffsetDateTime.now(), OffsetDateTime.now());

        given(topicService.addTopic(any(TopicDataDTO.class), any(Authentication.class))).willReturn(createdTopic);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        mockMvc.perform(post("/api/topics")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(createdTopic.getId()))
                .andExpect(jsonPath("$.userId").value(createdTopic.getUserId()))
                .andExpect(jsonPath("$.name").value(topicData.name()))
                .andExpect(jsonPath("$.createdAt").value(createdTopic.getCreatedAt().format(formatter)))
                .andExpect(jsonPath("$.updatedAt").value(createdTopic.getUpdatedAt().format(formatter)));
    }

    @WithMockUser(value = "mockUser")
    @Test
    public void whenPostToTopicsAndEmptyNameThenBadRequest() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO("");

        given(topicService.addTopic(any(TopicDataDTO.class), any(Authentication.class))).
                willReturn(new Topic(1, 2, "New topic", OffsetDateTime.now(), OffsetDateTime.now()));

        mockMvc.perform(post("/api/topics")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(topicService, times(0)).addTopic(eq(topicData), any(Authentication.class));
    }

    @WithMockUser(value = "mockUser")
    @Test
    public void whenPostToTopicsAndNullNameThenBadRequest() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO(null);

        given(topicService.addTopic(any(TopicDataDTO.class), any(Authentication.class))).
                willReturn(new Topic(1, 2, "New topic", OffsetDateTime.now(), OffsetDateTime.now()));

        mockMvc.perform(post("/api/topics")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(topicService, times(0)).addTopic(eq(topicData), any(Authentication.class));
    }

    @WithMockUser(value = "mockUser", roles = "ADMIN")
    @Test
    public void whenDeleteToTopicsAndRoleAdminThenOk() throws Exception {
        doNothing().when(topicService).deleteOne(any(Integer.class));

        mockMvc.perform(delete("/api/topics/1")
                        .with(csrf()))
                .andExpect(status().isOk());

        verify(topicService, times(1)).deleteOne(1);
    }

    @WithMockUser(value = "mockUser", roles = "USER")
    @Test
    public void whenDeleteToTopicsAndRoleUserThenForbidden() throws Exception {
        doNothing().when(topicService).deleteOne(any(Integer.class));

        mockMvc.perform(delete("/api/topics/1")
                        .with(csrf()))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Access Denied"));

        verify(topicService, times(0)).deleteOne(1);
    }

    @WithMockUser(value = "mockUser", roles = "ADMIN")
    @Test
    public void whenDeleteToTopicsAndTopicNotFoundThenNotFound() throws Exception {
        String message = "Topic not found";
        doThrow(new ResourceNotFoundException(message)).when(topicService).deleteOne(any(Integer.class));

        mockMvc.perform(delete("/api/topics/1")
                        .with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(message));

        verify(topicService, times(1)).deleteOne(1);
    }

    @WithMockUser(value = "mockUser", roles = "USER")
    @Test
    public void whenPutTopicsAndRoleUserThenForbidden() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO("New topic");

        given(topicService.updateTopic(any(Integer.class), any(TopicDataDTO.class))).
                willReturn(new Topic(1, 2, "New topic", OffsetDateTime.now(), OffsetDateTime.now()));

        mockMvc.perform(put("/api/topics/1")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Access Denied"));

        verify(topicService, times(0)).updateTopic(1, topicData);
    }

    @WithMockUser(value = "mockUser", roles = "ADMIN")
    @Test
    public void whenPutTopicsAndEmptyTopicNameThenBadRequest() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO("");

        given(topicService.updateTopic(any(Integer.class), any(TopicDataDTO.class))).
                willReturn(new Topic(1, 2, "New topic", OffsetDateTime.now(), OffsetDateTime.now()));

        mockMvc.perform(put("/api/topics/1")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(topicService, times(0)).updateTopic(1, topicData);
    }

    @WithMockUser(value = "mockUser", roles = "ADMIN")
    @Test
    public void whenPutTopicsAndNullTopicNameThenBadRequest() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO(null);

        given(topicService.updateTopic(any(Integer.class), any(TopicDataDTO.class))).
                willReturn(new Topic(1, 2, "New topic", OffsetDateTime.now(), OffsetDateTime.now()));

        mockMvc.perform(put("/api/topics/1")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(topicService, times(0)).updateTopic(1, topicData);
    }

    @WithMockUser(value = "mockUser", roles = "ADMIN")
    @Test
    public void whenPutToTopicsAndTopicNotFoundThenNotFound() throws Exception {
        String errorMessage = "Topic not found";
        TopicDataDTO topicData = new TopicDataDTO("New topic");

        given(topicService.updateTopic(any(Integer.class), any(TopicDataDTO.class))).
                willThrow(new ResourceNotFoundException(errorMessage));

        mockMvc.perform(put("/api/topics/1")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorMessage));

        verify(topicService, times(1)).updateTopic(1, topicData);
    }

    @WithMockUser(value = "mockUser", roles = "ADMIN")
    @Test
    public void whenPutTopicsAndValidTopicResponseIsCorrect() throws Exception {
        TopicDataDTO topicData = new TopicDataDTO("New topic");

        Topic updatedTopic = new Topic(1, 2, topicData.name(), OffsetDateTime.now(), OffsetDateTime.now());

        given(topicService.updateTopic(any(Integer.class), any(TopicDataDTO.class))).willReturn(updatedTopic);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

        mockMvc.perform(put("/api/topics/1")
                        .with(csrf())
                        .content(objectMapper.writeValueAsString(topicData))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedTopic.getId()))
                .andExpect(jsonPath("$.userId").value(updatedTopic.getUserId()))
                .andExpect(jsonPath("$.name").value(topicData.name()))
                .andExpect(jsonPath("$.createdAt").value(updatedTopic.getCreatedAt().format(formatter)))
                .andExpect(jsonPath("$.updatedAt").value(updatedTopic.getUpdatedAt().format(formatter)));
    }
}

