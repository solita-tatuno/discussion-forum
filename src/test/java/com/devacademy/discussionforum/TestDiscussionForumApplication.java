package com.devacademy.discussionforum;

import com.devacademy.discussionforum.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@EnableConfigurationProperties(RsaKeyProperties.class)
public class TestDiscussionForumApplication {

	public static void main(String[] args) {
		SpringApplication.from(DiscussionForumApplication::main).with(TestDiscussionForumApplication.class).run(args);
	}
}
