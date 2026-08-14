package com.tss.URL_Shortening.entity;

import com.tss.URL_Shortening.enums.UrlStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "short_urls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrl {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, columnDefinition = "TEXT")
        private String originalUrl;

        @Column(nullable = false, unique = true, length = 20)
        private String shortCode;

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        private User user;

        @Column
        private Integer visitsLimit=100;
        @Column
        private Integer visitsCount;

        @Enumerated(EnumType.STRING)
        private UrlStatus status;

        @Column(nullable = true)
        private String qrLink;




}
