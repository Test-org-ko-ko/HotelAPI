package com.api.hotel.service;

import com.api.hotel.constant.Constant;
import com.api.hotel.model.*;
import com.api.hotel.repo.BookingRepo;
import com.api.hotel.repo.PaymentRepo;
import com.api.hotel.repo.RoomTransitionRepo;
import com.api.hotel.repo.VisitorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class BookingServiceImpl implements BookingService {
    private static final Logger LOG = LoggerFactory.getLogger(BookingServiceImpl.class);
    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private VisitorRepo visitorRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RoomTransitionRepo transitionRepo;

    @Override
    public Booking createBooking(Booking bookingDetails, Room room) {
        BigDecimal amountPaid = null;
        try {
            BigDecimal pricePerDay = BigDecimal.valueOf(100); //room.getPrice();
            long days = ChronoUnit.DAYS.between(bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate());
            amountPaid = pricePerDay.multiply(BigDecimal.valueOf(days));
            String paymentMsg = paymentService.makePayment(Constant.PaymentReason.BOOKING, amountPaid);

            if (Constant.Payment.SUCCESS.equals(paymentMsg)) {
                String guestName = null;
                if (bookingDetails.getGuest() != null && !bookingDetails.getGuest().isBlank()) {
                    guestName = bookingDetails.getGuest();
                }
                else {
                    guestName = bookingDetails.getBookingGuest();
                }

                Visitor visitor = visitorRepo.save(new Visitor(guestName, bookingDetails.getEmail(),
                        bookingDetails.getPhone()));
                if (visitor == null || visitor.getVisitorId() == 0)
                    throw new Exception(Constant.Visitor.FAILED);

                Booking booking = bookingRepo.save(bookingDetails);
                if (booking == null || booking.getId() == 0)
                    throw new Exception(Constant.Booking.FAILED);

                RoomTransition transition = transitionRepo.save(new RoomTransition(bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate(),
                        bookingDetails.getRoomId(), visitor.getVisitorId(), amountPaid, booking.getId()));
                if (transition == null || transition.getRecordId() == 0)
                    throw new Exception(Constant.RoomTransition.FAILED);

                StringBuilder emailBookingBody = new StringBuilder("Hi ");
                emailBookingBody.append(guestName);
                emailBookingBody.append(",");
                emailBookingBody.append("\n");
                emailBookingBody.append("Your booking is confirmed.\n");
                emailBookingBody.append("Booking ID: ");
                emailBookingBody.append(booking.getId());
                emailBookingBody.append("\n");
                emailBookingBody.append("Hope to see you soon.\n");
                emailBookingBody.append("Hotel Planet");
                String bookingEmailStatus = emailService.sendMail(new Email(bookingDetails.getEmail(), Constant.Booking.SUBJECT,
                        emailBookingBody.toString(), null));
                if (Constant.Email.FAILED.equals(bookingEmailStatus))
                    throw new Exception(Constant.Email.FAILED);

                StringBuilder emailPaymentBody = new StringBuilder("Hi ");
                emailPaymentBody.append(guestName);
                emailPaymentBody.append(",");
                emailPaymentBody.append("\n");
                emailPaymentBody.append("Your Payment is received.\n");
                emailPaymentBody.append("Amount Paid: ");
                emailPaymentBody.append(amountPaid);
                emailPaymentBody.append("\n");
                emailPaymentBody.append("Hope to see you soon.\n");
                emailPaymentBody.append("Hotel Planet");
                String paymentEmailStatus = emailService.sendMail(new Email(bookingDetails.getEmail(), Constant.Payment.SUBJECT,
                        emailPaymentBody.toString(), null));
                if (Constant.Email.FAILED.equals(paymentEmailStatus))
                    throw new Exception(Constant.Email.FAILED);

                return booking;
            }
            else {
                throw new Exception(Constant.Payment.FAILED);
            }
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }

        return new Booking();
    }
}
