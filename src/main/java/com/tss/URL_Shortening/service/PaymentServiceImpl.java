package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.payment.PaymentRequestDto;
import com.tss.URL_Shortening.dto.payment.PaymentResponseDto;
import com.tss.URL_Shortening.entity.Payment;
import com.tss.URL_Shortening.entity.Purchase;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.enums.OfferType;
import com.tss.URL_Shortening.enums.PaymentStatus;
import com.tss.URL_Shortening.repository.PaymentRepository;
import com.tss.URL_Shortening.repository.PurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{



        private final PaymentRepository paymentRepository;
        private final PurchaseRepository purchaseRepository;

        @Override
        public PaymentResponseDto createPayment(
                PaymentRequestDto request) {

            Purchase purchase =
                    purchaseRepository.findById(request.getPurchaseId())
                            .orElseThrow(() ->
                                    new RuntimeException("Purchase not found"));

            Payment payment = Payment.builder()
                    .user(purchase.getUser())
                    .purchase(purchase)
                    .paymentGateway(request.getPaymentGateway())
                    .gatewayPaymentId(request.getGatewayPaymentId())
                    .amount(purchase.getTotalAmount())
                    .currency(request.getCurrency())
                    .status(PaymentStatus.PENDING)
                    .createdAt(LocalDateTime.now())
                    .build();

            return convertToDto(
                    paymentRepository.save(payment));
        }

        @Override
        public PaymentResponseDto markPaymentSuccess(
                Long paymentId,
                String gatewayPaymentId) {

            Payment payment =
                    paymentRepository.findById(paymentId)
                            .orElseThrow(() ->
                                    new RuntimeException("Payment not found"));

            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setGatewayPaymentId(gatewayPaymentId);
            payment.setPaidAt(LocalDateTime.now());

            applyPurchaseBenefit(payment);

            return convertToDto(
                    paymentRepository.save(payment));
        }

        @Override
        public PaymentResponseDto markPaymentFailed(
                Long paymentId) {

            Payment payment =
                    paymentRepository.findById(paymentId)
                            .orElseThrow(() ->
                                    new RuntimeException("Payment not found"));

            payment.setStatus(PaymentStatus.FAILED);

            return convertToDto(
                    paymentRepository.save(payment));
        }

        private void applyPurchaseBenefit(
                Payment payment) {

            Purchase purchase = payment.getPurchase();

            User user = purchase.getUser();

            if (purchase.getBusinessOffer().getType()
                    == OfferType.LINK_SLOT) {

                int currentSlots =
                        user.getLinkSlots() == null
                                ? 0
                                : user.getLinkSlots();

                int additionalSlots =
                        purchase.getBusinessOffer().getValue()
                                * purchase.getQuantity();

                user.setLinkSlots(
                        currentSlots + additionalSlots);
            }

            /*
             * VISITS logic will be added here.
             *
             * It will increase:
             *
             * url.visitsLimit
             *
             * and reactivate an expired URL.
             */
        }

        private PaymentResponseDto convertToDto(
                Payment payment) {

            return PaymentResponseDto.builder()
                    .id(payment.getId())
                    .userId(payment.getUser().getUserId())
                    .purchaseId(payment.getPurchase().getId())
                    .paymentGateway(payment.getPaymentGateway())
                    .gatewayPaymentId(payment.getGatewayPaymentId())
                    .amount(payment.getAmount())
                    .currency(payment.getCurrency())
                    .status(payment.getStatus())
                    .paidAt(payment.getPaidAt())
                    .createdAt(payment.getCreatedAt())
                    .build();
        }


}
