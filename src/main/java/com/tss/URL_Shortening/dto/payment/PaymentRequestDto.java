package com.tss.URL_Shortening.dto.payment;

import lombok.Data;

@Data
public class PaymentRequestDto {

        private Long purchaseId;

        private String paymentGateway;

        private String gatewayPaymentId;

        private String currency;

}
