package com.tss.URL_Shortening.dto.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSystemConfigRequestDto {
    @NotBlank(message = "Config value is required")
    @Size(max = 500, message = "Config value cannot exceed 500 characters")
    private String configValue;

    private String description;
}
