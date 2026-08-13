package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.ratelimit.RateLimitConfigResponseDto;
import com.tss.URL_Shortening.dto.ratelimit.UpdateRateLimitConfigRequestDto;
import com.tss.URL_Shortening.entity.RateLimitConfig;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.mapper.RateLimitConfigMapper;
import com.tss.URL_Shortening.repository.RateLimitConfigRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RateLimitServiceImpl implements RateLimitService{
    private final RateLimitConfigRepository rateLimitConfigRepository;
    private final RateLimitConfigMapper rateLimitConfigMapper;
    private final UserRepository userRepository;

    @Override
    public PageDto<RateLimitConfigResponseDto> getAllRateLimits(Pageable pageable) {

        Page<RateLimitConfig> rateLimitConfigPage=rateLimitConfigRepository.findAll(pageable);

        List<RateLimitConfigResponseDto> responseDtos=new ArrayList<>();

        for (RateLimitConfig rateLimitConfig:rateLimitConfigPage){
            RateLimitConfigResponseDto dto=rateLimitConfigMapper.toDto(rateLimitConfig);
            responseDtos.add(dto);
        }

        PageDto<RateLimitConfigResponseDto> pageDto=new PageDto<>();

        pageDto.setContent(responseDtos);
        pageDto.setCurrentPage(rateLimitConfigPage.getNumber());
        pageDto.setPageSize(rateLimitConfigPage.getSize());
        pageDto.setTotalPages(rateLimitConfigPage.getTotalPages());
        pageDto.setTotalElements(rateLimitConfigPage.getTotalElements());
        pageDto.setFirst(rateLimitConfigPage.isFirst());
        pageDto.setLast(rateLimitConfigPage.isLast());
        pageDto.setEmpty(rateLimitConfigPage.isEmpty());

        return pageDto;
    }

    @Override
    @Transactional
    public RateLimitConfigResponseDto updateRateLimit(String endpoint, UpdateRateLimitConfigRequestDto requestDto,String adminUserName) {

        RateLimitConfig config =
                rateLimitConfigRepository.findByEndpointKey(endpoint)
                        .orElseThrow(() -> new ResourceNotFoundException("Rate limit configuration not found for endpoint: " + endpoint));

        User admin = userRepository.findByUserName(adminUserName)
                .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

        config.setMaxRequests(requestDto.getMaxRequests());
        config.setWindowSeconds(requestDto.getWindowSeconds());
        config.setIsActive(requestDto.getIsActive());
        config.setUpdatedAt(LocalDateTime.now());
        config.setUpdatedBy(admin);

        RateLimitConfig savedConfig = rateLimitConfigRepository.save(config);

        return rateLimitConfigMapper.toDto(savedConfig);
    }
}
