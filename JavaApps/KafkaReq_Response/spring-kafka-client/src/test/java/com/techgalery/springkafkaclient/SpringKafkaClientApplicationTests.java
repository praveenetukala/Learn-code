package com.techgalery.springkafkaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.techgalery.springkafkaclient.service.KafkaService;

@SpringBootTest
class SpringKafkaClientApplicationTests {
	@Autowired
	KafkaService kafkaService;

}
