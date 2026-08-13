package com.tss.UrlShort.UrlShort.controller;


import com.tss.UrlShort.UrlShort.dto.BusinessOfferResponseDto;
import com.tss.UrlShort.UrlShort.dto.UserRegisterRequestDto;
import com.tss.UrlShort.UrlShort.dto.UserResponseDto;
import com.tss.UrlShort.UrlShort.service.BusinessOfferService;
import com.tss.UrlShort.UrlShort.service.BusinessOfferServiceImpl;
import com.tss.UrlShort.UrlShort.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

        private final UserService userService;
        private final  BusinessOfferService businessOfferService;

        @PostMapping("/register")
        public ResponseEntity<UserResponseDto> registerUser(
                @RequestBody UserRegisterRequestDto request) {

            UserResponseDto response = userService.registerUser(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }


    @GetMapping("/offers")
    public ResponseEntity<List<BusinessOfferResponseDto>> getAllOffers() {

        return ResponseEntity.ok(
                businessOfferService.getActiveOffers()
        );
    }

}
