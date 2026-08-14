package com.tss.URL_Shortening.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateProfileRequestDto {
    @NotBlank(message = "Username is required")
    @Size(max = 100)
    private String userName;
}
