package com.api.hotel.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
    // LOG.info(); -> Info, LOG.debug(); -> Debug

    // pay
    // 1. Room_Transition obj, Visitor obj
    // 2. Send confirmation and payment emails
    // 3. save these two Email objects

}
