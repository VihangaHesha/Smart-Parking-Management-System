/**
 * @author supunmadhuranga
 * @created 2025-06-16
 * @project project
 */

// ReceiptService.java
package com.vpms.payment.service;

import com.vpms.payment.dto.ReceiptDTO;
import com.vpms.payment.exception.InvalidReceiptRequestException;

public interface ReceiptService {
    ReceiptDTO generateReceipt(String paymentId) throws InvalidReceiptRequestException;
}