package com.tss.URL_Shortening.entity;

import com.tss.URL_Shortening.enums.OfferType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "business_offers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessOffer {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;

        @Column(length = 1000)
        private String description;

        @Enumerated(EnumType.STRING)
        private OfferType type;

        private Integer value;

        private BigDecimal price;

        private Boolean active;


}
