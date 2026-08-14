package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.config.SystemConfigResponseDto;
import com.tss.URL_Shortening.dto.config.UpdateSystemConfigRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SystemConfigService {
    PageDto<SystemConfigResponseDto> getAllConfigurations(Pageable pageable);

    SystemConfigResponseDto getConfiguration(String key);

    SystemConfigResponseDto updateConfiguration(String key, UpdateSystemConfigRequestDto requestDto, String AdminUserName);
}
