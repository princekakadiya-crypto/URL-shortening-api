package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.auth.RegisterRequestDto;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.service.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody RegisterRequestDto registrationDto) {

        UserResponseDto response = authenticationService.register(registrationDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        String token = authenticationService.login(loginDto);
        return ResponseEntity.ok(token);
    }
}
