package com.api.hotel.constant;

public final class Constant {
    private Constant() {}

    public  class PaymentReason {
        public static final String BOOKING = "Booking";
        public static final String FINE = "Fine";
    }

    public class Payment {
        public static final String SUCCESS = "Payment Success";
        public static final String FAILED = "Payment failed";
        public static final String SUBJECT = "Payment Confirmation";
    }

    public class Visitor {
        public static final String SUCCESS = "Visitor creation success";
        public static final String FAILED = "Visitor creation failed";
    }

    public class Booking {
        public static final String SUCCESS = "Booking creation success";
        public static final String FAILED = "Booking creation failed";
        public static final String SUBJECT = "Booking Confirmation";
        public static final String SUBJECT_UPDATE = "Booking Update Confirmation";
        public static final String SUBJECT_CANCEL = "Booking Cancellation Confirmation";
        public static final String UPDATE_SUCCESS = "Booking update success";
    }

    public class RoomTransition {
        public static final String SUCCESS = "RoomTransition creation success";
        public static final String FAILED = "RoomTransition creation failed";
    }

    public class Email {
        public static final String SUCCESS = "Email creation success";
        public static final String FAILED = "Email creation failed";
        public static final String EMAIL_FROM = "wintnandar.wnl@gmail.com";

    }

    public static final String DB_FAILED = "DB RETRIEVAL FAILED ";
    public static final String DB_UPDATE_FAILED = "DB UPDATE FAILED ";

}
