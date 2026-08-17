package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.report.AdminOverviewReportDto;
import com.tss.URL_Shortening.dto.report.AdminUrlReportDto;
import com.tss.URL_Shortening.dto.report.AdminUserReportDto;
import com.tss.URL_Shortening.dto.report.MyUrlReportDto;
import com.tss.URL_Shortening.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/reports/my/urls")
    public ResponseEntity<MyUrlReportDto> getMyUrlReport(Authentication authentication) {

        String adminUserName= authentication.getName();
        return ResponseEntity.ok(
                reportService.getMyUrlReport(adminUserName)
        );
    }

    @GetMapping("/admin/reports/overview")
    public ResponseEntity<AdminOverviewReportDto> getAdminOverviewReport() {

        return ResponseEntity.ok(
                reportService.getAdminOverviewReport()
        );
    }

    @GetMapping("/admin/reports/users")
    public ResponseEntity<AdminUserReportDto> getAdminUserReport() {

        return ResponseEntity.ok(
                reportService.getAdminUserReport()
        );
    }

    @GetMapping("/admin/reports/urls")
    public ResponseEntity<AdminUrlReportDto> getAdminUrlReport() {

        return ResponseEntity.ok(
                reportService.getAdminUrlReport()
        );
    }
}
