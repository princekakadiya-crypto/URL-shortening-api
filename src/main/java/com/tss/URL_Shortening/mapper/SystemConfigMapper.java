package com.tss.URL_Shortening.mapper;

import com.tss.URL_Shortening.dto.config.SystemConfigResponseDto;
import com.tss.URL_Shortening.entity.SystemConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SystemConfigMapper {
    @Mapping(target = "updatedBy", source = "updatedBy.userId")
    SystemConfigResponseDto toDto(SystemConfig systemConfig);
}
