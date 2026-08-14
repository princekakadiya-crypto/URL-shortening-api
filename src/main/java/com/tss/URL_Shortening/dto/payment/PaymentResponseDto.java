package com.tss.URL_Shortening.dto.payment;

import com.tss.URL_Shortening.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDto {



        private Long id;

        private Long userId;

        private Long purchaseId;

        private String paymentGateway;

        private String gatewayPaymentId;

        private BigDecimal amount;

        private String currency;

        private PaymentStatus status;

        private LocalDateTime paidAt;

        private LocalDateTime createdAt;



}
