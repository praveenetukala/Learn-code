package com.techgalery.springkafkaclient.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

/**
 * This class is used to send data to kafka request and take the response from
 * kafka
 * 
 * @author praveen etukala
 *
 */
@Service
public class KafkaService {

	@Autowired
	private ReplyingKafkaTemplate<String, Object, Object> template;

	@Value("${myproject.send-topics}")
	private String SEND_TOPICS;

	public void kafkaRequestReply(String request) {
		try {

			ProducerRecord<String, Object> record = new ProducerRecord<>(SEND_TOPICS, request);

			// Create a header
			String uuid = UUID.randomUUID().toString().replace("-", "");
			RecordHeader customHeader = new RecordHeader("unique_key", uuid.getBytes());

			// Add the header to the record
			record.headers().add(customHeader);

			RequestReplyFuture<String, Object, Object> replyFuture = template.sendAndReceive(record);

			SendResult<String, Object> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);

			ConsumerRecord<String, Object> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
			String responseKey = consumerRecord.value().toString();

			System.out.println("Request key--- " + uuid);
			System.out.println("Response key--- " + responseKey);

			if (uuid.equals(responseKey)) {
				System.out.println("Request and Response key's Matched-----------");
				// TODO Further process
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
