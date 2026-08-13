package com.tss.UrlShort.UrlShort.dto;

import com.tss.UrlShort.UrlShort.enums.OfferType;
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
