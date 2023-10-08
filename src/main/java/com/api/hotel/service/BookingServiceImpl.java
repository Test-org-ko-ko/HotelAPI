package com.api.hotel.service;

import com.api.hotel.model.Booking;
import com.api.hotel.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepo bookingRepo;
    @Override
    public Booking createBooking(Booking bookingDetails) {
        return bookingRepo.save(bookingDetails);
    }
}
