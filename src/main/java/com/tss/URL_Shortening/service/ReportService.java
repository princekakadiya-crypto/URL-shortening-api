package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.report.AdminOverviewReportDto;
import com.tss.URL_Shortening.dto.report.AdminUrlReportDto;
import com.tss.URL_Shortening.dto.report.AdminUserReportDto;
import com.tss.URL_Shortening.dto.report.MyUrlReportDto;

public interface ReportService {
    MyUrlReportDto getMyUrlReport(String adminUserName);

    AdminOverviewReportDto getAdminOverviewReport();

    AdminUserReportDto getAdminUserReport();

    AdminUrlReportDto getAdminUrlReport();
}
