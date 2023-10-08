package com.api.hotel.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="guest_name")
    private String guestName;

    @Column(name="email")
    private String email;

    @Column (name="check_in_date")
    private LocalDate checkInDate;

    @Column (name="check_out_date")
    private LocalDate checkOutDate;

    @Column (name="room_type")
    private String roomType;

    @Column (name="pay_now")
    private String payNow;

    public Booking(){

    }

    public Booking(int id, String name, String guestName, String email, LocalDate checkInDate, LocalDate checkOutDate, String roomType, String payNow) {
        this.id = id;
        this.name = name;
        this.guestName = guestName;
        this.email = email;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.roomType = roomType;
        this.payNow = payNow;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", guestName='" + guestName + '\'' +
                ", email='" + email + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", roomType='" + roomType + '\'' +
                ", payNow='" + payNow + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getPayNow() {
        return payNow;
    }

    public void setPayNow(String payNow) {
        this.payNow = payNow;
    }
}
