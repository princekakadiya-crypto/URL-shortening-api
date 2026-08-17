package com.tss.URL_Shortening.controller.admin;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.service.AdminUrlService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/shorturls")
@AllArgsConstructor
public class AdminUrlController {


        private final AdminUrlService adminService;


        @GetMapping
        public ResponseEntity<Page<ShortUrlResponseDto>> fetchShortUrls(
                Pageable pageable) {

            Page<ShortUrlResponseDto> shortUrls =
                    adminService.getAllShortUrls(pageable);

            return ResponseEntity.ok(shortUrls);
        }


        @GetMapping("/{shortUrlId}")
        public ResponseEntity<ShortUrlResponseDto> fetchShortUrl(
                @PathVariable Long shortUrlId) {

            ShortUrlResponseDto shortUrl =
                    adminService.getShortUrlById(shortUrlId);

            return ResponseEntity.ok(shortUrl);
        }


        @DeleteMapping("/{shortUrlId}")
        public ResponseEntity<Void> removeShortUrl(
                @PathVariable Long shortUrlId) {

            adminService.deleteShortUrl(shortUrlId);

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        }


 
        @PatchMapping("/{shortUrlId}/restore")
        public ResponseEntity<ShortUrlResponseDto> reactivateShortUrl(
                @PathVariable Long shortUrlId) {

            ShortUrlResponseDto restoredUrl =
                    adminService.restoreShortUrl(shortUrlId);

            return ResponseEntity.ok(restoredUrl);
        }

}
