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
@RequestMapping("/api/v1/admin/urls")
@AllArgsConstructor
public class AdminUrlController {

//
//        private final AdminUrlService adminUrlService;
//
//        @GetMapping
//        public ResponseEntity<PageDto<ShortUrlResponseDto>> getAllUrls(Pageable pageable) {
//
//            return ResponseEntity.ok(adminUrlService.getAllUrls(pageable));
//        }
//
//        @GetMapping("/{id}")
//        public ResponseEntity<ShortUrlResponseDto> getUrlById(@PathVariable Long id) {
//
//            return ResponseEntity.ok(adminUrlService.getUrlById(id));
//        }
//
//        @DeleteMapping("/{id}")
//        public ResponseEntity<Void> deleteUrl(@PathVariable Long id) {
//
//            adminUrlService.deleteUrl(id);
//            return ResponseEntity.noContent().build();
//        }
//
//        @PatchMapping("/{id}/restore")
//        public ResponseEntity<ShortUrlResponseDto> restoreUrl(@PathVariable Long id) {
//
//            return ResponseEntity.ok(adminUrlService.restoreUrl(id));
//        }

}
