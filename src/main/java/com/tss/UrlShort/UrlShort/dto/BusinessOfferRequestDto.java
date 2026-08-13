package com.tss.UrlShort.UrlShort.dto;

import com.tss.UrlShort.UrlShort.enums.OfferType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BusinessOfferRequestDto {

        private String name;

        private String description;

        private OfferType type;

        private Integer value;

        private BigDecimal price;

        private Boolean active;

}
