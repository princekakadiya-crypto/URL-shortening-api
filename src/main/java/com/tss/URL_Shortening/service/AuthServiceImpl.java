package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.cache.SystemConfigCache;
import com.tss.URL_Shortening.dto.auth.*;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.entity.OtpVerification;
import com.tss.URL_Shortening.entity.Role;
import com.tss.URL_Shortening.entity.TokenBlacklist;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.enums.OtpPurpose;
import com.tss.URL_Shortening.exception.*;
import com.tss.URL_Shortening.mapper.UserMapper;
import com.tss.URL_Shortening.repository.OtpVerificationRepository;
import com.tss.URL_Shortening.repository.RoleRepository;
import com.tss.URL_Shortening.repository.TokenBlacklistRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import com.tss.URL_Shortening.security.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final OtpVerificationRepository otpVerificationRepository;
    private final EmailService emailService;
    private final TokenBlacklistRepository tokenBlacklistRepository;
    private final SystemConfigCache configCache;

    @Override
    @Transactional
    public UserResponseDto register(RegisterRequestDto requestDto) {

        int minPasswordLength = configCache.getInt("MIN_PASSWORD_LENGTH");

        if (requestDto.getPassword().length() < minPasswordLength) {
            throw new InvalidOperationException("New password must be at least " + minPasswordLength + " characters");
        }

        if (userRepository.existsByEmail(requestDto.getEmail()))
            throw new DuplicateResourceException("Email is already Exists");

        if (userRepository.existsByUserName(requestDto.getUserName()))
            throw new DuplicateResourceException("User Name is already Exists");

        Role role=roleRepository.findByRoleName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default USER role not found"));

        User user = new User();

        user.setUserName(requestDto.getUserName());
        user.setEmail(requestDto.getEmail());
        user.setPasswordHash(
                passwordEncoder.encode(requestDto.getPassword())
        );
        user.setEmailVerified(false);
        user.setRole(role);

        User savedUser=userRepository.save(user);

        String otp = generateOtp();
        OtpVerification otpVerification = new OtpVerification();

        otpVerification.setUser(user);
        otpVerification.setOtpHash(otp);
        otpVerification.setPurpose(OtpPurpose.PASSWORD_RESET);
        otpVerification.setAttempts(0);
        otpVerification.setMaxAttempts(configCache.getInt("OTP_MAX_ATTEMPTS"));
        otpVerification.setCreatedAt(LocalDateTime.now());
        otpVerification.setExpiresAt(LocalDateTime.now().plusMinutes(
                configCache.getInt("OTP_EXPIRY_MINUTES")
        ));

        otpVerificationRepository.save(otpVerification);

        emailService.sendEmailVerificationOtp(savedUser.getEmail(), savedUser.getUserName(), otp);

        return userMapper.toDto(savedUser);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto requestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUserName(), requestDto.getPassword()));
            //SecurityContextHolder getContext().setAuthentication(authentication);

            String token = jwtTokenProvider.generateToken(authentication);
            LoginResponseDto responseDto=new LoginResponseDto();
            responseDto.setToken(token);
            responseDto.setTokenType("Bearer");
            return responseDto;
        } catch (BadCredentialsException e) {
            throw new InvalidCredentialException("Username or Password is incorrect");
        }
    }

    @Override
    @Transactional
    public void verifyEmail(VerifyEmailRequestDto requestDto) {

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (Boolean.TRUE.equals(user.getEmailVerified())) {
            throw new InvalidOperationException("Email is already verified");
        }

        OtpVerification otpVerification = otpVerificationRepository.findValidLatestOtp(
                user.getUserId(),OtpPurpose.EMAIL_VERIFICATION.name())
                .orElseThrow(() -> new InvalidCredentialException("Invalid Otp"));

        user.setEmailVerified(true);

        userRepository.save(user);
        otpVerificationRepository.save(otpVerification);
    }

    @Override
    @Transactional
    public void logout(String token) {

        if (token == null || token.isBlank()) {
            throw new InvalidCredentialException("Token is required");
        }

        // Remove "Bearer " if it is passed with the token
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        // Validate JWT
        if (!jwtTokenProvider.validateToken(token)) {
            throw new InvalidCredentialException("Invalid or expired token");
        }

        // Get user from JWT
        String username = jwtTokenProvider.getUsername(token);

        User user = userRepository
                .findByUserName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Hash token before storing
        String tokenHash = hashToken(token);

        // Prevent duplicate blacklist entry
        if (tokenBlacklistRepository.existsByTokenHash(tokenHash)) {
            return;
        }

        TokenBlacklist tokenBlacklist = new TokenBlacklist();
        tokenBlacklist.setTokenHash(tokenHash);
        tokenBlacklist.setExpiresAt(jwtTokenProvider.getExpirationDateFromToken(token));
        tokenBlacklist.setBlacklistedAt(LocalDateTime.now());
        tokenBlacklist.setUser(user);

        tokenBlacklistRepository.save(tokenBlacklist);
    }

    @Override
    @Transactional
    public void forgotPassword(ForgotPasswordRequestDto requestDto) {

        Optional<User> userOptional = userRepository.findByEmail(requestDto.getEmail());

        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }

        User user = userOptional.get();

        if (!Boolean.TRUE.equals(user.getIsActive())) {
            throw new InvalidOperationException("Email is not verified");
        }

        String otp = generateOtp();

        OtpVerification otpVerification = new OtpVerification();

        otpVerification.setUser(user);
        otpVerification.setOtpHash(otp);
        otpVerification.setPurpose(OtpPurpose.PASSWORD_RESET);
        otpVerification.setAttempts(0);
        otpVerification.setMaxAttempts(configCache.getInt("OTP_MAX_ATTEMPTS"));
        otpVerification.setCreatedAt(LocalDateTime.now());
        otpVerification.setExpiresAt(LocalDateTime.now().plusMinutes(configCache.getInt("OTP_EXPIRY_MINUTES")));

        otpVerificationRepository.save(otpVerification);

        emailService.sendPasswordResetOtp(user.getEmail(), user.getUserName(), otp);
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequestDto requestDto) {

        int minPasswordLength = configCache.getInt("MIN_PASSWORD_LENGTH");

        if (requestDto.getNewPassword().length() < minPasswordLength) {
            throw new InvalidOperationException("New password must be at least " + minPasswordLength + " characters");
        }

        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new InvalidCredentialException("Invalid email"));

        OtpVerification otpVerification = otpVerificationRepository
                .findValidLatestOtp(user.getUserId(), OtpPurpose.PASSWORD_RESET.name())
                .orElseThrow(() -> new InvalidOtpException("Invalid Otp"));

        // Change password
        user.setPasswordHash(passwordEncoder.encode(requestDto.getNewPassword()));

        otpVerification.setVerifiedAt(LocalDateTime.now());

        userRepository.save(user);
        otpVerificationRepository.save(otpVerification);
    }

    @Override
    public void changePassword(ChangePasswordRequestDto request,Authentication authentication) {

        int minPasswordLength = configCache.getInt("MIN_PASSWORD_LENGTH");

        if (request.getNewPassword().length() < minPasswordLength) {
            throw new InvalidOperationException("New password must be at least " + minPasswordLength + " characters");
        }

        String userName = authentication.getName();

        // Find user
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Check old password
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialException("Old password is incorrect");
        }

        // Don't allow same password
        if (passwordEncoder.matches(request.getNewPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialException("New password must be different from old password");
        }

        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);
    }

    private String generateOtp() {
        return String.format("%06d", new SecureRandom().nextInt(1_000_000));
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(token.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 algorithm not available", e);
        }
    }
}
