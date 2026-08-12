package com.tss.URL_Shortening.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendEmailVerificationOtp(String email, String userName, String otp) {
        String subject = "Verify Your Email";

        String body = """
                Hello %s,

                Your email verification OTP is:

                %s

                This OTP will expire in 10 minutes.

                If you did not create this account, please ignore this email.

                Regards,
                URL Shortening Team
                """.formatted(userName, otp);

        sendEmail(email, subject, body);
    }

    @Override
    public void sendPasswordResetOtp(String email, String userName, String otp) {
        String subject = "Password Reset OTP";

        String body = """
                Hello %s,

                Your password reset OTP is:

                %s

                This OTP will expire in 10 minutes.

                If you did not request a password reset, please ignore this email.

                Regards,
                URL Shortening Team
                """.formatted(userName, otp);

        sendEmail(email, subject, body);
    }

    private void sendEmail(String to, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
