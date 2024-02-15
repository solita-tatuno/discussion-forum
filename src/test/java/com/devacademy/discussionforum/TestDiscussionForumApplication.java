package com.devacademy.discussionforum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestDiscussionForumApplication {

	public static void main(String[] args) {
		SpringApplication.from(DiscussionForumApplication::main).with(TestDiscussionForumApplication.class).run(args);
	}
}
