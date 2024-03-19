package com.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SMPTService {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String toEmail, String subject, String message) {
		System.out.println("SMPTService.sendMail()");
		try {

			SimpleMailMessage mailMessage = new SimpleMailMessage();

			mailMessage.setTo(toEmail);
			mailMessage.setSubject(subject);
			mailMessage.setText(message);

			mailMessage.setFrom("praveenetukala3@gmail.com");

			javaMailSender.send(mailMessage);
		} catch (Exception e) {
			System.out.println("Exception occured while sending message --->" + e.getMessage());
			e.printStackTrace();
		}

	}
}
