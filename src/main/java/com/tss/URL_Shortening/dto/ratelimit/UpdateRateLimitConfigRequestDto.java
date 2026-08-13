package com.tss.URL_Shortening.dto.ratelimit;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRateLimitConfigRequestDto {
    @NotNull(message = "Max requests is required")
    @Min(value = 1, message = "Max requests must be greater than 0")
    private Integer maxRequests;

    @NotNull(message = "Window seconds is required")
    @Min(value = 1, message = "Window seconds must be greater than 0")
    private Integer windowSeconds;

    @NotNull(message = "Active status is required")
    private Boolean isActive;
}
