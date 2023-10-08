package com.api.hotel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private BigDecimal amount;

    @Column(name = "payment_reason")
    private String paymentReason;

    @Column(name = "email_id")
    private int emailId;

    public Payment() {}

    public Payment(BigDecimal amount, String paymentReason, int emailId) {
        this.amount = amount;
        this.paymentReason = paymentReason;
        this.emailId = emailId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPaymentReason() {
        return paymentReason;
    }

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment payment)) return false;
        return id == payment.id && emailId == payment.emailId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailId);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentReason='" + paymentReason + '\'' +
                ", emailId=" + emailId +
                '}';
    }
}
