package com.tss.URL_Shortening.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rate_limit_config")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RateLimitConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rate_limit_id")
    private Long rateLimitId;

    @Column(name = "endpoint_key", nullable = false, unique = true, length = 100)
    private String endpointKey;

    @Column(name = "max_requests", nullable = false)
    private Integer maxRequests;

    @Column(name = "window_seconds", nullable = false)
    private Integer windowSeconds;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
