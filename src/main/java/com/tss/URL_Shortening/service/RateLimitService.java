package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.ratelimit.RateLimitConfigResponseDto;
import com.tss.URL_Shortening.dto.ratelimit.UpdateRateLimitConfigRequestDto;
import org.springframework.data.domain.Pageable;

public interface RateLimitService {
    PageDto<RateLimitConfigResponseDto> getAllRateLimits(Pageable pageable);

    RateLimitConfigResponseDto updateRateLimit(String endpoint, UpdateRateLimitConfigRequestDto requestDto,String AdminUserName);
}
