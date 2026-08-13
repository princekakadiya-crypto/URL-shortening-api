package com.tss.UrlShort.UrlShort.dto;

import lombok.Data;

@Data
public class PurchaseRequestDto {

        private Long userId;

        private Long businessOfferId;

        private Long urlId;

        private Integer quantity;

}
