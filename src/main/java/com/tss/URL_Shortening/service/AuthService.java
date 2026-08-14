package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.auth.*;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import org.springframework.security.core.Authentication;

public interface AuthService {

    UserResponseDto register(RegisterRequestDto request);

    void verifyEmail(VerifyEmailRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto request);

    void logout(String authorizationHeader);

    void forgotPassword(ForgotPasswordRequestDto request);

    void resetPassword(ResetPasswordRequestDto request);

    void changePassword(ChangePasswordRequestDto request, Authentication authentication);
}
