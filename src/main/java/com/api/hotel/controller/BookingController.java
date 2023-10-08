package com.api.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hotel.service.RoomService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private RoomService roomService;
	
	// make booking
	// create booking obj
	// return to API consumer for redirecting to payment page
	
	
}
