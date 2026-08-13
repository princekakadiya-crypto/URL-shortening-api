package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.config.SystemConfigResponseDto;
import com.tss.URL_Shortening.dto.config.UpdateSystemConfigRequestDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.SystemConfig;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.mapper.SystemConfigMapper;
import com.tss.URL_Shortening.repository.SystemConfigRepository;
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
public class SystemConfigServiceImpl implements SystemConfigService {

    private final SystemConfigRepository systemConfigRepository;
    private final UserRepository userRepository;
    private final SystemConfigMapper systemConfigMapper;

    @Override
    public PageDto<SystemConfigResponseDto> getAllConfigurations(Pageable pageable) {

        Page<SystemConfig> systemConfigs=systemConfigRepository.findAll(pageable);

        List<SystemConfigResponseDto> responseDtos=new ArrayList<>();

        for (SystemConfig systemConfig:systemConfigs){
            SystemConfigResponseDto dto=systemConfigMapper.toDto(systemConfig);
            responseDtos.add(dto);
        }

        PageDto<SystemConfigResponseDto> pageDto = new PageDto<>();

        pageDto.setContent(responseDtos);
        pageDto.setCurrentPage(systemConfigs.getNumber());
        pageDto.setPageSize(systemConfigs.getSize());
        pageDto.setTotalPages(systemConfigs.getTotalPages());
        pageDto.setTotalElements(systemConfigs.getTotalElements());
        pageDto.setFirst(systemConfigs.isFirst());
        pageDto.setLast(systemConfigs.isLast());
        pageDto.setEmpty(systemConfigs.isEmpty());

        return pageDto;


    }

    @Override
    @Transactional
    public SystemConfigResponseDto getConfiguration(String key) {

        SystemConfig config = systemConfigRepository.findByConfigKey(key)
                        .orElseThrow(() -> new ResourceNotFoundException("Configuration not found: " + key));

        return systemConfigMapper.toDto(config);
    }


    @Override
    @Transactional
    public SystemConfigResponseDto updateConfiguration(String key, UpdateSystemConfigRequestDto requestDto, String adminUserName) {

        SystemConfig config = systemConfigRepository.findByConfigKey(key)
                .orElseThrow(() -> new ResourceNotFoundException("Configuration not found: " + key));

        User admin = userRepository.findByUserName(adminUserName)
                        .orElseThrow(() -> new ResourceNotFoundException("Admin user not found"));

        config.setConfigValue(requestDto.getConfigValue());
        config.setDescription(requestDto.getDescription());
        config.setUpdatedAt(LocalDateTime.now());

        config.setUpdatedBy(admin);

        SystemConfig savedConfig = systemConfigRepository.save(config);

        return systemConfigMapper.toDto(savedConfig);
    }
}
