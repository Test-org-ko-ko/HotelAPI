package com.api.hotel.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String type;
	
	private BigDecimal price;
	
	@Column(name = "room_number")
	private int roomNumber;
	
	@Column(name = "is_available")
	private String isAvailable;
	
	public Room() {}
	
	public Room(String type, BigDecimal price, int roomNumber, String isAvailable) {
		this.type = type;
		this.price = price;
		this.roomNumber = roomNumber;
		this.isAvailable = isAvailable;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String isAvailable() {
		return isAvailable;
	}
	public void setAvailable(String isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roomNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return id == other.id && Objects.equals(roomNumber, other.roomNumber);
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", type=" + type + ", price=" + price + ", roomNumber=" + roomNumber
				+ ", isAvailable=" + isAvailable + "]";
	}

	
}
