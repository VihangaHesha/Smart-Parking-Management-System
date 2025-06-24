package com.vpms.payment.dto;

import com.vpms.payment.model.Payment.PaymentStatus;
import lombok.Data;

@Data
public class PaymentStatusUpdateDTO {
    private PaymentStatus status;
    private String transactionId;
    private String failureReason;
}