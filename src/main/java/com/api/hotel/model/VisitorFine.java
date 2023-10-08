package com.api.hotel.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Visitor_Fine")
public class VisitorFine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String reason;

    @Column(name = "visitor_id")
    private int visitorId;

    @Column(name = "payment_id")
    private int paymentId;

    @Column(name = "transition_id")
    private int transitionId;

    public VisitorFine(String reason, int visitorId, int paymentId, int transitionId) {
        this.reason = reason;
        this.visitorId = visitorId;
        this.paymentId = paymentId;
        this.transitionId = transitionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(int visitorId) {
        this.visitorId = visitorId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getTransitionId() {
        return transitionId;
    }

    public void setTransitionId(int transitionId) {
        this.transitionId = transitionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitorFine that)) return false;
        return id == that.id && transitionId == that.transitionId && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reason, transitionId);
    }

    @Override
    public String toString() {
        return "VisitorFine{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", visitorId=" + visitorId +
                ", paymentId=" + paymentId +
                ", transitionId=" + transitionId +
                '}';
    }
}
