package com.api.hotel.controller;

import com.api.hotel.repo.BookingRepo;
import com.api.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hotel.service.RoomService;
import com.api.hotel.model.Booking;
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private RoomService roomService;

	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private BookingService bookingService;
	// make booking
	@PostMapping("/bookings")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking bookingDetails){
		Booking createBooking = bookingService.createBooking(bookingDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(createBooking);
	}
	// update booking
	
	
	// delete booking
	
	
	// payment for booking via payment service
	
	
}
