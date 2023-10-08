package com.api.hotel.service;

import com.api.hotel.model.Payment;

import java.math.BigDecimal;

public interface PaymentService {
	public String makePayment(String reason, BigDecimal amount);

	public Payment makeFinePayment(String reason, BigDecimal amount);
	// send email via email service
}
