package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.payment.PaymentRequestDto;
import com.tss.URL_Shortening.dto.payment.PaymentResponseDto;
import com.tss.URL_Shortening.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/paymets")
@RequiredArgsConstructor
public class PaymentController {



        private final PaymentService paymentService;

        @PostMapping
        public ResponseEntity<PaymentResponseDto> createPayment(
                @RequestBody PaymentRequestDto requestDto) {

            return ResponseEntity.ok(
                    paymentService.createPayment(requestDto));
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
