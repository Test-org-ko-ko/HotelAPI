package com.api.hotel.service;

import com.api.hotel.model.Booking;
import com.api.hotel.model.Room;
import com.api.hotel.model.RoomType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookingService {
    public Booking createBooking(Booking bookingDetails, Room room);
    public String updateBooking(Booking bookingDetails);
    public String cancelBooking(Integer id);
    public List<Booking> getAllBookings(boolean canceled);
}
