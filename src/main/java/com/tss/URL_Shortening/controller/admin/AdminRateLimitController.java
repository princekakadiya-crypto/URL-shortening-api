package com.tss.URL_Shortening.controller.admin;


import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.ratelimit.RateLimitConfigResponseDto;
import com.tss.URL_Shortening.dto.ratelimit.UpdateRateLimitConfigRequestDto;
import com.tss.URL_Shortening.service.RateLimitConfigService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/rate-limits")
@AllArgsConstructor
public class AdminRateLimitController {

    private final RateLimitConfigService rateLimitService;

    @GetMapping
    public ResponseEntity<PageDto<RateLimitConfigResponseDto>> getAllRateLimits(Pageable pageable) {

        return ResponseEntity.ok(
                rateLimitService.getAllRateLimits(pageable)
        );
    }

    @PutMapping("/{endpoint}")
    public ResponseEntity<RateLimitConfigResponseDto> updateRateLimit(
            @PathVariable String endpoint,
            @Valid @RequestBody UpdateRateLimitConfigRequestDto requestDto,
            Authentication authentication) {

        String adminUserName= authentication.getName();

        return ResponseEntity.ok(
                rateLimitService.updateRateLimit(endpoint, requestDto, adminUserName)
        );
    }

}
