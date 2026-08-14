package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.url.CreateShortUrlRequestDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.service.ShortUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class ShortUrlController {





        private final ShortUrlService shortUrlService;

        @PostMapping("/shorten")
        public ResponseEntity<ShortUrlResponseDto> shortenUrl(
                @RequestBody CreateShortUrlRequestDto request) {

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
