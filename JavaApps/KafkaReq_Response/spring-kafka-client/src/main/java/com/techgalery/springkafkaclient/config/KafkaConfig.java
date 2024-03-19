package com.techgalery.springkafkaclient.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class KafkaConfig {

	@Value("${myproject.reply-topics}")
	private String REPLY_TOPICS;

	@Value("${myproject.consumer-group}")
	private String CONSUMER_GROUPS;

	// register and configure replying kafka template
	@Bean
	public ReplyingKafkaTemplate<String, Object, Object> replyingTemplate(ProducerFactory<String, Object> pf,
			ConcurrentMessageListenerContainer<String, Object> repliesContainer) {
		ReplyingKafkaTemplate<String, Object, Object> replyTemplate = new ReplyingKafkaTemplate<>(pf, repliesContainer);
		replyTemplate.setDefaultReplyTimeout(Duration.ofSeconds(10));// it will wait for response till time
		replyTemplate.setSharedReplyTopic(true);
		return replyTemplate;
	}

	// register ConcurrentMessageListenerContainer bean
	@Bean
	public ConcurrentMessageListenerContainer<String, Object> repliesContainer(
			ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
		ConcurrentMessageListenerContainer<String, Object> repliesContainer = containerFactory
				.createContainer(REPLY_TOPICS);
		repliesContainer.getContainerProperties().setGroupId(CONSUMER_GROUPS);
		repliesContainer.setAutoStartup(false);
		return repliesContainer;
	}
}
