package com.api.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.api.hotel.model.Email;



@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	@Value("$(spring.mail.username)")
	private String fromMail;
	@Override
	public String sendMail(String mail, Email details) {
		try {	
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setFrom(fromMail);
		        mailMessage.setTo(mail);
		        mailMessage.setText(details.getBody());
		        mailMessage.setSubject(details.getSubject());
			    javaMailSender.send(mailMessage);
			    return "Mail sent Successfully";
			}
		catch(Exception ex) {
				return "Email was not sent!";
		}
	}
}
