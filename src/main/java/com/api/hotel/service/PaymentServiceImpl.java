package com.api.hotel.service;

import com.api.hotel.model.Payment;
import com.api.hotel.repo.PaymentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.hotel.constant.Constant;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    PaymentRepo paymentRepo;

    @Override
    public String makePayment(String reason, BigDecimal amount) {
        Payment paymentMade = pay(reason, amount);
        return paymentMade == null ? Constant.Payment.FAILED : Constant.Payment.SUCCESS;
    }

    @Override
    public Payment makeFinePayment(String reason, BigDecimal amount) {
        return pay(reason, amount);
    }

    private Payment pay(String reason, BigDecimal amount) {
        Payment paymentMade = null;
        try {
            return paymentRepo.save(new Payment(amount, reason, 0));
        }
        catch (Exception e) {
            LOG.error(Constant.DB_FAILED + e.getMessage(), e);
        }
        return null;
    }


}
