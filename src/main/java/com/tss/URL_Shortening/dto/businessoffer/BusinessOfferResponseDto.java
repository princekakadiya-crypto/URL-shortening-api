package com.tss.URL_Shortening.dto.businessoffer;

import com.tss.URL_Shortening.enums.OfferType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class BusinessOfferResponseDto {

        private Long id;

        private String name;

        private String description;

        private OfferType type;

        private Integer value;

        private BigDecimal price;

        private Boolean active;


}
