package com.api.hotel.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "booking_guest")
    private String bookingGuest;

    @Transient
    private String guest;

    @Transient
    private String email;
    @Transient
    private String phone;

    @Column(name = "check_in_date")
    private LocalDate checkInDate;

    @Column(name = "check_out_date")
    private LocalDate checkOutDate;

    @Column(name = "room_id")
    private int roomId;

    @Column(name = "pay_now")
    private String payNow;

    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "email_id")
    private int emailId;

    public Booking() {}

    public Booking(String bookingGuest, String guest, LocalDate checkInDate, LocalDate checkOutDate, int roomId, String payNow,
                   int paymentId, int emailId, String email, String phone) {
        this.bookingGuest = bookingGuest;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomId = roomId;
        this.payNow = payNow;
        this.paymentId = paymentId;
        this.emailId = emailId;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingGuest() {
        return bookingGuest;
    }

    public void setBookingGuest(String bookingGuest) {
        this.bookingGuest = bookingGuest;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getPayNow() {
        return payNow;
    }

    public void setPayNow(String payNow) {
        this.payNow = payNow;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking booking)) return false;
        return id == booking.id && Objects.equals(bookingGuest, booking.bookingGuest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookingGuest);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingGuest='" + bookingGuest + '\'' +
                ", guest='" + guest + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", roomId=" + roomId +
                ", payNow='" + payNow + '\'' +
                ", paymentId=" + paymentId +
                ", emailId=" + emailId +
                '}';
    }
}
