package com.api.hotel.service;

import com.api.hotel.model.Payment;

import java.math.BigDecimal;

public interface PaymentService {
	public String makePayment(String reason, BigDecimal amount);

	public Payment makeFinePayment(String reason, BigDecimal amount);

	public Payment updatePayment(Integer paymentId, BigDecimal amount) throws Exception;

}
