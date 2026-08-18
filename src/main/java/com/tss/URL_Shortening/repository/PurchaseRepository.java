package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.dto.report.MyPurchaseReportDto;
import com.tss.URL_Shortening.entity.Purchase;
import com.tss.URL_Shortening.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {



    List<Purchase> findByUser(User user);

    @Query(value = """
    SELECT
        COUNT(*) AS totalPurchases,
        COALESCE(SUM(quantity), 0) AS totalQuantity,
        COALESCE(SUM(total_amount), 0) AS totalAmount,
        COALESCE(AVG(total_amount), 0) AS averagePurchaseAmount
    FROM purchases
    WHERE user_id = :userId
    """, nativeQuery = true)
    MyPurchaseReportDto getMyPurchaseReport(Long userId);

}
