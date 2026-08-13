package com.tss.URL_Shortening.dto.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemConfigResponseDto {
    private Long configId;

    private String configKey;

    private String configValue;

    private String description;

    private LocalDateTime updatedAt;

    private Long updatedBy;
}
