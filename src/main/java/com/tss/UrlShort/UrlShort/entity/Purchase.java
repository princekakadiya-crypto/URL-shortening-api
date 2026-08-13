package com.tss.UrlShort.UrlShort.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Purchase {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @ManyToOne
        @JoinColumn(name = "business_offer_id", nullable = false)
        private BusinessOffer businessOffer;

        @ManyToOne
        @JoinColumn(name = "url_id")
        private Url url;

        private Integer quantity;

        private BigDecimal totalAmount;

        private String transactionId;

        private LocalDateTime createdAt;

}
