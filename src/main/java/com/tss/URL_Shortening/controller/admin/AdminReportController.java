package com.tss.URL_Shortening.controller.admin;

import com.tss.URL_Shortening.dto.report.AdminOverviewReportDto;
import com.tss.URL_Shortening.dto.report.AdminUrlReportDto;
import com.tss.URL_Shortening.dto.report.AdminUserReportDto;
import com.tss.URL_Shortening.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/reports")
public class AdminReportController {
    private final ReportService reportService;
    @GetMapping("/overview")
    public ResponseEntity<AdminOverviewReportDto> getAdminOverviewReport() {

        return ResponseEntity.ok(
                reportService.getAdminOverviewReport()
        );
    }

    @GetMapping("/users")
    public ResponseEntity<AdminUserReportDto> getAdminUserReport() {

        return ResponseEntity.ok(
                reportService.getAdminUserReport()
        );
    }

    @GetMapping("/urls")
    public ResponseEntity<AdminUrlReportDto> getAdminUrlReport() {

        return ResponseEntity.ok(
                reportService.getAdminUrlReport()
        );
    }
}
