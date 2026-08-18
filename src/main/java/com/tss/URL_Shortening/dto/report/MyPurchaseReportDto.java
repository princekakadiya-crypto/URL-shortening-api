package com.tss.URL_Shortening.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPurchaseReportDto {
    private Long totalPurchases;
    private Long totalQuantity;
    private BigDecimal totalAmount;
    private BigDecimal averagePurchaseAmount;
}
