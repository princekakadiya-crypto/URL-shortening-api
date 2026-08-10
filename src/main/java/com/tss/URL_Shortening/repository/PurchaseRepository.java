package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
