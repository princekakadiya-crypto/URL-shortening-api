package com.tss.URL_Shortening.controller.admin;


import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.config.SystemConfigResponseDto;
import com.tss.URL_Shortening.dto.config.UpdateSystemConfigRequestDto;
import com.tss.URL_Shortening.service.SystemConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/config")
@RequiredArgsConstructor
public class AdminConfigController {

    private final SystemConfigService systemConfigService;

    @GetMapping
    public ResponseEntity<PageDto<SystemConfigResponseDto>> getAllConfigurations(Pageable pageable) {

        return ResponseEntity.ok(
                systemConfigService.getAllConfigurations(pageable)
        );
    }

    @GetMapping("/{key}")
    public ResponseEntity<SystemConfigResponseDto> getConfiguration(@PathVariable String key) {

        return ResponseEntity.ok(
                systemConfigService.getConfiguration(key)
        );
    }

    @PutMapping("/{key}")
    public ResponseEntity<SystemConfigResponseDto> updateConfiguration(
            @PathVariable String key, @Valid @RequestBody UpdateSystemConfigRequestDto requestDto, Authentication authentication) {

        String adminUserName= authentication.getName();

        return ResponseEntity.ok(
                systemConfigService.updateConfiguration(key, requestDto, adminUserName)
        );
    }
}
