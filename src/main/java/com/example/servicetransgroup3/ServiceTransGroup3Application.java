package com.example.servicetransgroup3;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
		org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class}
)
@EnableCaching
public class ServiceTransGroup3Application {
	final String TOPIC = "u4k85isn-default";

	public static void main(String[] args) {
		SpringApplication.run(ServiceTransGroup3Application.class, args);
	}

//	@Bean
//	NewTopic createTopic() {
//		return TopicBuilder.name(TOPIC + "_CREATE").partitions(1).replicas(3).build();
//	}
}
