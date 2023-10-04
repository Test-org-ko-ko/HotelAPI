package com.api.hotel.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Visitor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int visitorId;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	public Visitor() {}

	public Visitor(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public int getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public int hashCode() {
		return Objects.hash(name, visitorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visitor other = (Visitor) obj;
		return Objects.equals(name, other.name) && visitorId == other.visitorId;
	}

	@Override
	public String toString() {
		return "Visitor [visitorId=" + visitorId + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}
	

}
