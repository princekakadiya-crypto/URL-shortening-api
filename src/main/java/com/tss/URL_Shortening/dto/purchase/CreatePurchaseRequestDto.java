package com.tss.URL_Shortening.dto.purchase;

import lombok.Data;

@Data
public class CreatePurchaseRequestDto {


        private Long userId;

        private Long businessOfferId;

        private Long urlId;

        private Integer quantity;


}
