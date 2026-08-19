package com.tss.URL_Shortening.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResendOtpRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
}
