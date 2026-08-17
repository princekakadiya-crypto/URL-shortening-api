package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.url.CreateShortUrlRequestDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.service.ShortUrlService;
import com.tss.URL_Shortening.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class ShortUrlController {





        private final ShortUrlService shortUrlService;
        private  final UserService userService;

        @PostMapping("/shorten")
        public ResponseEntity<ShortUrlResponseDto> shortenUrl(
                @RequestBody CreateShortUrlRequestDto request, Authentication authentication) {

            String username = authentication.getName();
            Optional<User> user = userService.findByName(username);
            Long id = user.get().getUserId();
            request.setUserId(id);

            ShortUrlResponseDto response =
                    shortUrlService.shortenUrl(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }

        @GetMapping("/{shortCode}")
        public ResponseEntity<Void> redirectToOriginalUrl(
                @PathVariable String shortCode) {

            ShortUrl shortUrl =
                    shortUrlService.getShortUrl(shortCode);

            shortUrlService.recordVisit(shortUrl);

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(URI.create(shortUrl.getOriginalUrl()))
                    .build();
        }



        @GetMapping("/qr/{urlId}")
        public ResponseEntity<Void> redirectToOriginalUrl(
                @PathVariable Long urlId) {

            ShortUrl shortUrl =
                    shortUrlService.getUrlById(urlId);

            shortUrlService.recordVisit(shortUrl);

            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .location(URI.create(shortUrl.getQrLink()))
                    .build();
        }



}
