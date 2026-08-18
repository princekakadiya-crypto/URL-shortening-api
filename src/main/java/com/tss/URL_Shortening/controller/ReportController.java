package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.report.*;
import com.tss.URL_Shortening.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reports")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/my/urls")
    public ResponseEntity<MyUrlReportDto> getMyUrlReport(Authentication authentication) {

        String adminUserName= authentication.getName();
        return ResponseEntity.ok(
                reportService.getMyUrlReport(adminUserName)
        );
    }

    @GetMapping("/my/purchases")
    public ResponseEntity<MyPurchaseReportDto> getMyPurchaseReport(Authentication authentication) {

        return ResponseEntity.ok(
                reportService.getMyPurchaseReport(authentication.getName())
        );
    }

}
