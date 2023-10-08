package com.api.hotel.model;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Email")
public class Email {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "recipient")
	private String recipient;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "attachments")
	private String attachments;
	
	public Email() {}

	public Email(String recipient, String subject, String body, String attachments) {
		this.recipient = recipient;
		this.subject=subject;
		this.body=body;
		this.attachments=attachments;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getAttachments() {
		return attachments;
	}
	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
	@Override
	public int hashCode() {
		return Objects.hash(attachments, body, id, recipient, subject);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(attachments, other.attachments) && Objects.equals(body, other.body) && id == other.id
				&& Objects.equals(recipient, other.recipient) && Objects.equals(subject, other.subject);
	}
	@Override
	public String toString() {
		return "Email [id=" + id + ", recipient=" + recipient + ", subject=" + subject + ", body=" + body
				+ ", attachments=" + attachments + "]";
	}
	
	
}
