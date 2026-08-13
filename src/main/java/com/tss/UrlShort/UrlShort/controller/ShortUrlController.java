package com.tss.UrlShort.UrlShort.controller;

import com.tss.UrlShort.UrlShort.dto.ShortUrlRequest;
import com.tss.UrlShort.UrlShort.dto.ShortUrlResponseDto;
import com.tss.UrlShort.UrlShort.entity.Url;
import com.tss.UrlShort.UrlShort.service.ShortUrlService;
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
                @RequestBody ShortUrlRequest request) {

            ShortUrlResponseDto response =
                    shortUrlService.shortenUrl(request);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(response);
        }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirectToOriginalUrl(
            @PathVariable String shortCode) {

        Url shortUrl =
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

        Url shortUrl =
                shortUrlService.getUrlById(urlId);

        shortUrlService.recordVisit(shortUrl);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(shortUrl.getQrLink()))
                .build();
    }


}
