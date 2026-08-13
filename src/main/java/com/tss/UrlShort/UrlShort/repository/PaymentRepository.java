package com.tss.UrlShort.UrlShort.repository;

import com.tss.UrlShort.UrlShort.entity.Payment;
import com.tss.UrlShort.UrlShort.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository  extends JpaRepository<Payment,Long> {

        Optional<Payment> findByPurchase(Purchase purchase);

        Optional<Payment> findByGatewayPaymentId(String gatewayPaymentId);

}
