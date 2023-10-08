package com.api.hotel.service;

import com.api.hotel.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.hotel.model.Email;

@Service
public class EmailServiceImpl implements EmailService {
	private static final Logger LOG = LoggerFactory.getLogger(EmailServiceImpl.class);
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendMail(Email details) {
		try {	
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(Constant.Email.EMAIL_FROM);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getBody());
			mailMessage.setSubject(details.getSubject());
			javaMailSender.send(mailMessage);
			return Constant.Email.SUCCESS;
			}
		catch(Exception ex) {
			LOG.error(Constant.Email.FAILED + ex.getMessage(), ex);
			return Constant.Email.FAILED;
		}
	}
}
