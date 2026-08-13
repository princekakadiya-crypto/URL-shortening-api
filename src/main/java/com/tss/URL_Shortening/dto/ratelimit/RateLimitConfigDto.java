package com.tss.URL_Shortening.dto.ratelimit;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RateLimitConfigDto {
    private Integer maxRequests;

    private Integer windowSeconds;

    private Boolean isActive;
}
