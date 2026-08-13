package com.tss.UrlShort.UrlShort.controller;


import com.tss.UrlShort.UrlShort.dto.PaymentRequestDto;
import com.tss.UrlShort.UrlShort.dto.PaymentResponseDto;
import com.tss.UrlShort.UrlShort.service.PaymentService;
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
