package com.tss.URL_Shortening.cache;

import com.tss.URL_Shortening.dto.ratelimit.RateLimitConfigDto;
import com.tss.URL_Shortening.entity.RateLimitConfig;
import com.tss.URL_Shortening.repository.RateLimitConfigRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RateLimitConfigCache {

    private final RateLimitConfigRepository rateLimitConfigRepository;

    private Map<String, RateLimitConfigDto> configs = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadConfigs() {

        List<RateLimitConfig> rateLimitConfigs=rateLimitConfigRepository.findAll();

        for (RateLimitConfig rateLimitConfig:rateLimitConfigs){

            RateLimitConfigDto dto=new RateLimitConfigDto(
                    rateLimitConfig.getMaxRequests(),
                    rateLimitConfig.getWindowSeconds(),
                    rateLimitConfig.getIsActive()
            );

            configs.put(rateLimitConfig.getEndpointKey(),dto);
        }
    }

    public RateLimitConfigDto get(String endpointKey) {
        return configs.get(endpointKey);
    }

    public void update(String endpointKey, RateLimitConfigDto config) {
        configs.put(endpointKey, config);
    }
}
