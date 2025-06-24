package com.vpms.payment.controller;

import com.vpms.payment.dto.*;
import com.vpms.payment.dto.PaymentRequestDTO;
import com.vpms.payment.dto.PaymentResponseDTO;
import com.vpms.payment.dto.PaymentStatusUpdateDTO;
import com.vpms.payment.dto.ReceiptDTO;
import com.vpms.payment.exception.InvalidReceiptRequestException;
import com.vpms.payment.exception.PaymentConflictException;
import com.vpms.payment.exception.PaymentNotFoundException;
import com.vpms.payment.service.PaymentService;
import com.vpms.payment.service.ReceiptService;
import com.vpms.payment.util.ReceiptGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ReceiptService receiptService;

    @Autowired
    private ReceiptGenerator pdfGenerator;


    @Autowired
    public PaymentController(PaymentService paymentService, ReceiptService receiptService) {
        this.paymentService = paymentService;
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDTO> createPayment(@Valid @RequestBody PaymentRequestDTO dto) {
        try {
            PaymentResponseDTO response = paymentService.createPayment(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (PaymentConflictException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable("id") Long id) {
        try {
            PaymentResponseDTO response = paymentService.getPaymentById(id);
            return ResponseEntity.ok(response);
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public List<PaymentResponseDTO> getPaymentsByUser(@PathVariable("userId") Long userId) {
        return paymentService.getPaymentsByUser(userId);
    }

    @PatchMapping("/{paymentId}/status")
    public ResponseEntity<PaymentResponseDTO> updatePaymentStatus(
            @PathVariable("paymentId") String paymentId,
            @Valid @RequestBody PaymentStatusUpdateDTO dto) {
        try {
            PaymentResponseDTO response = paymentService.updatePaymentStatus(paymentId, dto);
            return ResponseEntity.ok(response);
        } catch (PaymentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/{paymentId}/receipt", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable("paymentId") String paymentId) {
        try {
            ReceiptDTO receipt = receiptService.generateReceipt(paymentId);
            byte[] pdfData = pdfGenerator.generatePdf(receipt);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition
                    .attachment()
                    .filename("receipt_" + paymentId + ".pdf")
                    .build());

            return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);

        } catch (PaymentNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidReceiptRequestException e) {
            byte[] pdfError = pdfGenerator.generatePdf(createErrorReceipt(e.getMessage()));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfError);
        }
    }

    private ReceiptDTO createErrorReceipt(String errorMessage) {
        ReceiptDTO errorReceipt = new ReceiptDTO();
        errorReceipt.setReceiptNote("Error: " + errorMessage);
        return errorReceipt;
    }
}