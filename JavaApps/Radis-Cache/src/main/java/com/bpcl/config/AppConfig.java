package com.bpcl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.bpcl.dto.Student;

@Configuration
public class AppConfig {

	// Radis Connection
	@Bean
	public RedisConnectionFactory cf() {
		return new LettuceConnectionFactory();
	}

	// Redis Template
	@Bean
	public RedisTemplate<String, Student> rt() {
		RedisTemplate<String, Student> template = new RedisTemplate<>();
		template.setConnectionFactory(this.cf());
		return template;
	}
}
