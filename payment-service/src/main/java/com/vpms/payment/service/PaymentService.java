/**
 * @author supunmadhuranga
 * @created 2025-06-16
 * @project project
 */

// PaymentService.java
package com.vpms.payment.service;

import com.vpms.payment.dto.PaymentRequestDTO;
import com.vpms.payment.dto.PaymentResponseDTO;
import com.vpms.payment.dto.PaymentStatusUpdateDTO;
import com.vpms.payment.model.Payment;

import java.util.List;

public interface PaymentService {
    PaymentResponseDTO createPayment(PaymentRequestDTO paymentRequestDTO);
    PaymentResponseDTO getPaymentById(Long id);
    List<PaymentResponseDTO> getPaymentsByUser(Long userId);
    PaymentResponseDTO updatePaymentStatus(String paymentId, PaymentStatusUpdateDTO statusUpdateDTO);
    Payment getPaymentEntityByPaymentId(String paymentId);
}