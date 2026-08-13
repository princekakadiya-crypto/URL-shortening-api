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
    @Column(name = "short_url_id")
    private Long shortUrlId;

    @Column(name = "long_url", nullable = false, columnDefinition = "TEXT")
    private String longUrl;

    @Column(name = "alias", nullable = false, unique = true, length = 100)
    private String alias;

    @Column(name = "total_visits", nullable = false)
    private Integer totalVisits;

    @Column(name = "remaining_visits", nullable = false)
    private Integer remainingVisits;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private UrlStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "last_accessed_at")
    private LocalDateTime lastAccessedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shortUrl")
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "shortUrl")
    private List<Image> images = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
