package com.tss.URL_Shortening.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailRequestDto {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "\\d{6}", message = "OTP must be 6 digits")
    private String otp;
}
