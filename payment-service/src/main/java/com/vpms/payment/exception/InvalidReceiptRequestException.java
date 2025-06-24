package com.vpms.payment.exception;

public class InvalidReceiptRequestException extends Exception {
    public InvalidReceiptRequestException(String message) {
        super(message);
    }
}