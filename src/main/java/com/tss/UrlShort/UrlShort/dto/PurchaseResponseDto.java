package com.tss.UrlShort.UrlShort.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseResponseDto {


        private Long id;

        private Long userId;

        private Long businessOfferId;

        private Long urlId;

        private Integer quantity;

        private BigDecimal totalAmount;

        private String transactionId;

        private LocalDateTime createdAt;

}
