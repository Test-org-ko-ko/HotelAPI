package com.api.hotel.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "Room_Transition")
public class RoomTransition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "check_in_date")
	private LocalDate checkInDate;
	
	@Column(name = "check_out_date")
	private LocalDate checkOutDate;
	
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name = "visitor_id")
	private int visitorId;
	
	@Column(name = "amount_paid")
	private BigDecimal amountPaid;

	@Column(name = "booking_id")
	private int bookingId;

	@Transient
	private Visitor visitor;

	public RoomTransition() {}

	public RoomTransition(LocalDate checkInDate, LocalDate checkOutDate, int roomId, int visitorId,
						  BigDecimal amountPaid, int bookingId, Visitor visitor) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomId = roomId;
		this.visitorId = visitorId;
		this.amountPaid = amountPaid;
		this.bookingId = bookingId;
		this.visitor = visitor;
	}
	public RoomTransition(LocalDate checkInDate, LocalDate checkOutDate, int roomId, int visitorId,
						  BigDecimal amountPaid, int bookingId) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomId = roomId;
		this.visitorId = visitorId;
		this.amountPaid = amountPaid;
		this.bookingId = bookingId;
	}

	public int getRecordId() {
		return id;
	}

	public void setRecordId(int recordId) {
		this.id = recordId;
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

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roomId, visitorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomTransition other = (RoomTransition) obj;
		return id == other.id && roomId == other.roomId && visitorId == other.visitorId;
	}

	@Override
	public String toString() {
		return "RoomTransition{" +
				"id=" + id +
				", checkInDate=" + checkInDate +
				", checkOutDate=" + checkOutDate +
				", roomId=" + roomId +
				", visitorId=" + visitorId +
				", amountPaid=" + amountPaid +
				", bookingId=" + bookingId +
				", visitor=" + visitor +
				'}';
	}
}
