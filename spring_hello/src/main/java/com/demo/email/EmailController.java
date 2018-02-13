package com.demo.email;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
	@Autowired
	private EmailService email;
	// @Autowired
	// private Mail mail;

	@RequestMapping(value = "/mail", method = RequestMethod.POST)
	public String mailsend(@RequestBody Mail mail) throws MessagingException, IOException {
		//Mail mail1 = new Mail(mail.getTo(),mail.getSubject(),mail.getContent());
		email.sendSimpleMessage(mail);
		return "success";
	}
}
