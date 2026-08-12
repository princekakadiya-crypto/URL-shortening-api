package com.tss.URL_Shortening.service;

public interface EmailService {
    void sendEmailVerificationOtp(String email, String userName, String otp);

    void sendPasswordResetOtp(String email, String userName, String otp);
}
