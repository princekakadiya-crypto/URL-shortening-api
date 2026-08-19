package com.tss.URL_Shortening.dto.auth;

import com.tss.URL_Shortening.cache.SystemConfigCache;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequestDto {

    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @NotBlank(message = "New password is required")
    @Size(max = 100, message = "New password must be less than or equal to 100 characters")
    private String newPassword;
}
