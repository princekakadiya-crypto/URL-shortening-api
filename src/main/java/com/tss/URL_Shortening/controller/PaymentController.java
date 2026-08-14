package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.payment.PaymentRequestDto;
import com.tss.URL_Shortening.dto.payment.PaymentResponseDto;
import com.tss.URL_Shortening.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(
            @RequestBody PaymentRequestDto request) {

        return ResponseEntity.ok(
                paymentService.createPayment(request));
    }

    @PutMapping("/{paymentId}/success")
    public ResponseEntity<PaymentResponseDto> paymentSuccess(
            @PathVariable Long paymentId,
            @RequestParam String gatewayPaymentId) {

        return ResponseEntity.ok(
                paymentService.markPaymentSuccess(
                        paymentId,
                        gatewayPaymentId));
    }

    @PutMapping("/{paymentId}/failed")
    public ResponseEntity<PaymentResponseDto> paymentFailed(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(
                paymentService.markPaymentFailed(paymentId));
    }

}
