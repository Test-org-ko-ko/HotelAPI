package com.api.hotel.service;

import com.api.hotel.Exception.ResourceNotFoundException;
import com.api.hotel.constant.Constant;
import com.api.hotel.model.*;
import com.api.hotel.repo.BookingRepo;
import com.api.hotel.repo.RoomTransitionRepo;
import com.api.hotel.repo.VisitorRepo;
import jakarta.persistence.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
        String paymentStatus ="";
        try {
            BigDecimal pricePerDay = BigDecimal.valueOf(100); //room.getPrice();
            long days = ChronoUnit.DAYS.between(bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate());
            amountPaid = pricePerDay.multiply(BigDecimal.valueOf(days));
            String paymentMsg = paymentService.makePayment(Constant.PaymentReason.BOOKING, amountPaid);

            if (Constant.Payment.SUCCESS.equals(paymentMsg)) {
                paymentStatus = Constant.Payment.SUCCESS;
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

                bookingDetails.setPaymentStatus(paymentStatus);
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

    @Override
    public String updateBooking(Booking bookingDetails) {
        BigDecimal updatePriceToBePaid = null;
        try {
            Booking booking = bookingRepo.findById(bookingDetails.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id : " + bookingDetails.getId()));
            booking.setCheckInDate(bookingDetails.getCheckInDate());
            booking.setCheckOutDate(bookingDetails.getCheckOutDate());
            booking.setRoomId(bookingDetails.getRoomId());
            Booking updateBooking = bookingRepo.saveAndFlush(booking);
            if (updateBooking == null || updateBooking.getId() == 0)
                throw new Exception(Constant.Booking.FAILED);

            BigDecimal pricePerDay = BigDecimal.valueOf(200); //room.getPrice();
            long days = ChronoUnit.DAYS.between(bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate());
            updatePriceToBePaid = pricePerDay.multiply(BigDecimal.valueOf(days));
            Payment paymentUpdated = paymentService.updatePayment(updateBooking.getPaymentId(), updatePriceToBePaid);
            if (paymentUpdated == null || paymentUpdated.getId() == 0)
                throw new Exception(Constant.Payment.FAILED);

            String guestName = null;
            if (bookingDetails.getGuest() != null && !bookingDetails.getGuest().isBlank()) {
                guestName = bookingDetails.getGuest();
            }
            else {
                guestName = bookingDetails.getBookingGuest();
            }
            //System.out.println("Guest Name " + guestName + ", id: " +bookingDetails.getId() + ", email: " + bookingDetails.getEmail());
            StringBuilder emailBookingBody = new StringBuilder("Hi ");
            emailBookingBody.append(guestName);
            emailBookingBody.append(",");
            emailBookingBody.append("\n");
            emailBookingBody.append("Your booking is modified.\n");
            emailBookingBody.append("Booking ID: ");
            emailBookingBody.append(bookingDetails.getId());
            emailBookingBody.append("\n");
            emailBookingBody.append("Hope to see you soon.\n");
            emailBookingBody.append("Hotel Planet");
            String bookingEmailStatus = emailService.sendMail(new Email(bookingDetails.getEmail(), Constant.Booking.SUBJECT_UPDATE,
                    emailBookingBody.toString(), null));
            if (Constant.Email.FAILED.equals(bookingEmailStatus))
                throw new Exception(Constant.Email.FAILED);

            StringBuilder emailPaymentBody = new StringBuilder("Hi ");
            emailPaymentBody.append(guestName);
            emailPaymentBody.append(",\n");
            emailPaymentBody.append("Your Payment is received.\n");
            emailPaymentBody.append("Updated Amount Paid: ");
            emailPaymentBody.append(updatePriceToBePaid);
            emailPaymentBody.append("\n");
            emailPaymentBody.append("Hope to see you soon.\nHotel Planet");
            String paymentEmailStatus = emailService.sendMail(new Email(bookingDetails.getEmail(), Constant.Payment.SUBJECT,
                    emailPaymentBody.toString(), null));
            if (Constant.Email.FAILED.equals(paymentEmailStatus))
                throw new Exception(Constant.Email.FAILED);

            return Constant.Booking.UPDATE_SUCCESS;
        }
        catch(Exception ex){
            LOG.error(ex.getMessage(), ex);
        }
        return Constant.DB_UPDATE_FAILED;
    }

    @Override
    public String cancelBooking(Integer id) {
        try {
            Booking booking = bookingRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not exist with id : " + id));

            booking.setCanceled(true);
            Booking canceledBooking = bookingRepo.saveAndFlush(booking);
            if (canceledBooking == null || canceledBooking.getId() == 0)
                throw new PersistenceException(Constant.DB_UPDATE_FAILED);

            String guestName = null;
            if (canceledBooking.getGuest() != null && !canceledBooking.getGuest().isBlank()) {
                guestName = canceledBooking.getGuest();
            }
            else {
                guestName = canceledBooking.getBookingGuest();
            }
            StringBuilder emailBookingBody = new StringBuilder("Hi ");
            emailBookingBody.append(guestName);
            emailBookingBody.append(",");
            emailBookingBody.append("\n");
            emailBookingBody.append("Your booking cancellation is confirmed.\n");
            emailBookingBody.append("Booking ID: ");
            emailBookingBody.append(canceledBooking.getId());
            emailBookingBody.append("\n.");
            emailBookingBody.append("Hope to see you again.\n");
            emailBookingBody.append("Hotel Planet");
            String bookingEmailStatus = emailService.sendMail(new Email(/*hard-coded, change later*/"takenheart7@gmail.com", Constant.Booking.SUBJECT_UPDATE,
                    emailBookingBody.toString(), null));
            if (Constant.Email.FAILED.equals(bookingEmailStatus))
                throw new Exception(Constant.Email.FAILED);

            return Constant.Booking.UPDATE_SUCCESS;
        }
        catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return Constant.DB_UPDATE_FAILED;
    }

    @Override
    public List<Booking> getAllBookings(boolean canceled) {
        try {
            System.out.println("in service...");
            List<Booking> bookings = bookingRepo.findAll();
            if (bookings != null && bookings.size() > 0) {
                if (!canceled) {
                    return bookings.stream()
                            .filter(booking -> !booking.isCanceled())
                            .toList();
                }

                System.out.println("bookings " + bookings);
                return bookings;
            }
        }
        catch (Exception e) {
            LOG.error(Constant.DB_FAILED + e.getMessage(), e);
        }
        return null;
    }
}
