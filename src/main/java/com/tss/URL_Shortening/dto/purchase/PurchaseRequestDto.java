package com.tss.URL_Shortening.dto.purchase;

import lombok.Data;


@Data
public class PurchaseRequestDto {

        private Long userId;

        private Long businessOfferId;

        private Long urlId;

        private Integer quantity;


}
