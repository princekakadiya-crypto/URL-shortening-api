package com.tss.UrlShort.UrlShort.service;


import com.tss.UrlShort.UrlShort.dto.PaymentRequestDto;
import com.tss.UrlShort.UrlShort.dto.PaymentResponseDto;

public interface PaymentService {


        PaymentResponseDto createPayment(
                PaymentRequestDto request);

        PaymentResponseDto markPaymentSuccess(
                Long paymentId,
                String gatewayPaymentId);

        PaymentResponseDto markPaymentFailed(
                Long paymentId);

}
