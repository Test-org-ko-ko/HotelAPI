package com.api.hotel.controller;

import com.api.hotel.constant.Constant;
import com.api.hotel.model.Email;
import com.api.hotel.model.Payment;
import com.api.hotel.model.VisitorFine;
import com.api.hotel.service.EmailService;
import com.api.hotel.service.PaymentService;
import com.api.hotel.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private EmailService emailService;

    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
    // LOG.info(); -> Info, LOG.debug(); -> Debug

    // pay fine
    @PostMapping("/fine/{amount}")
    public ResponseEntity<Boolean> payFine(@PathVariable("amount") BigDecimal amountPaid,
                                           @RequestBody VisitorFine visitorFine) {

        try {
            Payment payment = paymentService.makeFinePayment(Constant.PaymentReason.FINE, amountPaid);
            if (payment != null && payment.getId() != 0) {
                visitorFine.setPaymentId(payment.getId());
                if (!roomService.createVisitorFine(visitorFine))
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);

                StringBuilder emailBody = new StringBuilder("Hi ");
                emailBody.append(visitorFine.getPayerName());
                emailBody.append(",");
                emailBody.append("\n");
                emailBody.append("Your payment is received.\n");
                emailBody.append("Payment ID: ");
                emailBody.append(payment.getId());
                emailBody.append("\n");
                emailBody.append("Thank you.\n");
                emailBody.append("Hotel Planet");
                String emailStatus = emailService.sendMail(new Email(visitorFine.getPayerEmail(), Constant.Payment.SUBJECT,
                        emailBody.toString(), null));
                if (Constant.Email.FAILED.equals(emailStatus))
                    throw new Exception(Constant.Email.FAILED);

            }
            else {
                return ResponseEntity.status(HttpStatus.CREATED).body(true);
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

}
