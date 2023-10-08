package com.api.hotel.service;

import com.api.hotel.model.Booking;
import org.springframework.stereotype.Service;

public interface BookingService {
    public Booking createBooking(Booking bookingDetails);
}
