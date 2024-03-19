package com.techgalery.springkafkaclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techgalery.springkafkaclient.bean.Data;
import com.techgalery.springkafkaclient.service.KafkaService;

@RestController
public class MasterController {

	@Autowired
	private KafkaService kafkaService;

	@RequestMapping("/send")
	public void sendData() throws Exception {

		for (int i = 1; i <= 100; ++i) {
			Data data = new Data(i, "LearnCode");
			kafkaService.kafkaRequestReply(new ObjectMapper().writeValueAsString(data));
		}
	}
}
