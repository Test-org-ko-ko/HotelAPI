package com.api.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hotel.model.Email;
import com.api.hotel.service.EmailService;

@RestController
@RequestMapping("/mail")
public class EmailController {
	@Autowired private EmailService emailService;
	 
    @PostMapping("/send/{mail}")
    public String sendMail(@PathVariable String mail,@RequestBody Email emailDetails)
    {
        return emailService.sendMail(mail, emailDetails);
    }
}
