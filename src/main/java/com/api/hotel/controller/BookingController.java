package com.api.hotel.controller;

import com.api.hotel.Exception.ResourceNotFoundException;
import com.api.hotel.constant.Constant;
import com.api.hotel.model.Room;
import com.api.hotel.model.RoomType;
import com.api.hotel.repo.BookingRepo;
import com.api.hotel.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.hotel.service.RoomService;
import com.api.hotel.model.Booking;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private RoomService roomService;
	@Autowired
	private BookingRepo bookingRepo;
	@Autowired
	private BookingService bookingService;

	@PostMapping("/create")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking bookingDetails){
		Booking createBooking = bookingService.createBooking(bookingDetails, new Room());
		if (createBooking == null || createBooking.getId() == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createBooking);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createBooking);
	}

	// update booking
	// 1. update checkInDate, checkOutDate, RoomId
	// 2. update amountpaid in payment
	// 3. send email for successful update
	@PutMapping("/update")
	public ResponseEntity<String> updateBooking(@RequestBody Booking bookingDetails){
		String updateBookingStatus = bookingService.updateBooking(bookingDetails);
		if (!Constant.Booking.UPDATE_SUCCESS.equals(updateBookingStatus)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updateBookingStatus);
		}
		return ResponseEntity.status(HttpStatus.OK).body(updateBookingStatus);
	}

	@DeleteMapping("/cancel/{id}")
	public ResponseEntity<String> cancelBooking(@PathVariable Integer id) {
		String cancelBookingStatus = bookingService.cancelBooking(id);
		if (!Constant.Booking.UPDATE_SUCCESS.equals(cancelBookingStatus)) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cancelBookingStatus);
		}
		return ResponseEntity.status(HttpStatus.OK).body(cancelBookingStatus);
	}

	@GetMapping("/find/{canceled}")
	public ResponseEntity<List<Booking>> getBooking(@PathVariable boolean canceled) {
		return ResponseEntity.status(HttpStatus.OK).body(
				bookingService.getAllBookings(canceled)
		);
	}
	
}
