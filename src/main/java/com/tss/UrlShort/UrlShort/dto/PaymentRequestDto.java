package com.tss.UrlShort.UrlShort.dto;


import lombok.Data;

@Data
public class PaymentRequestDto {

        private Long purchaseId;

        private String paymentGateway;

        private String gatewayPaymentId;

        private String currency;

}
