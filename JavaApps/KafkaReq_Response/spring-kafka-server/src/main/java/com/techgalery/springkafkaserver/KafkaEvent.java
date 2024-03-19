package com.techgalery.springkafkaserver;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * This class is used to consume the data and send the response
 * 
 * @author Praveen etukala
 *
 */
@Component
public class KafkaEvent {

	@KafkaListener(groupId = "kafka.response", topics = "${myproject.send-topics}")
	@SendTo
	public Message<?> listen(ConsumerRecord<String, Object> consumerRecord, @Header("unique_key") String key) {
		try {
			System.out.println("Request key--" + key);
			// TODO
			String reversedString = new StringBuilder(String.valueOf(key)).toString();
			return MessageBuilder.withPayload(reversedString).build();
		} catch (Exception e) {
			e.printStackTrace();
			return MessageBuilder.withPayload(null).build();
		}
	}
}
