package com.tss.URL_Shortening.dto.ratelimit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateLimitConfigResponseDto {
    private Long rateLimitId;

    private String endpointKey;

    private Integer maxRequests;

    private Integer windowSeconds;

    private Boolean isActive;

    private Long updatedBy;

    private LocalDateTime updatedAt;
}
