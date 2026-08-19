package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.auth.*;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.exception.InvalidCredentialException;
import com.tss.URL_Shortening.service.AuthService;
import com.tss.URL_Shortening.service.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody RegisterRequestDto request) {

        return ResponseEntity.ok(
                authService.register(request)
        );
    }

    @PostMapping("/resend-verification-otp")
    public ResponseEntity<String> resendVerificationOtp(@Valid @RequestBody ResendOtpRequestDto request) {

        authService.resendVerificationOtp(request);

        return ResponseEntity.ok("Verification OTP sent successfully");
    }

    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@Valid @RequestBody VerifyEmailRequestDto requestDto) {

        authService.verifyEmail(requestDto);

        return ResponseEntity.ok("Email verified successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidCredentialException("Authorization token is required");
        }

        String token = authorizationHeader.substring(7);

        authService.logout(token);

        return ResponseEntity.ok("Logout successful");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDto request) {

        authService.forgotPassword(request);

        return ResponseEntity.ok("Password reset instructions sent successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody ResetPasswordRequestDto request) {

        authService.resetPassword(request);

        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@Valid @RequestBody ChangePasswordRequestDto request
            , Authentication authentication) {

        authService.changePassword(request,authentication);

        return ResponseEntity.ok("Password changed successfully");
    }
}
