package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.payment.PaymentRequestDto;
import com.tss.URL_Shortening.dto.payment.PaymentResponseDto;

public interface PaymentService {


    PaymentResponseDto createPayment(
            PaymentRequestDto request);

    PaymentResponseDto markPaymentSuccess(
            Long paymentId,
            String gatewayPaymentId);

    PaymentResponseDto markPaymentFailed(
            Long paymentId);

}
