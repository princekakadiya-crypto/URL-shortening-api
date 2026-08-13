package com.tss.URL_Shortening.mapper;

import com.tss.URL_Shortening.dto.ratelimit.RateLimitConfigResponseDto;
import com.tss.URL_Shortening.entity.RateLimitConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RateLimitConfigMapper {
    @Mapping(target = "updatedBy", source = "updatedBy.userId")
    RateLimitConfigResponseDto toDto(RateLimitConfig rateLimitConfig);
}
