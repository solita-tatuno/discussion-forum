package com.devacademy.discussionforum;

import com.devacademy.discussionforum.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class DiscussionForumApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscussionForumApplication.class, args);
	}

}
