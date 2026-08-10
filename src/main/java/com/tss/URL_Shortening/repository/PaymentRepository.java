package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
