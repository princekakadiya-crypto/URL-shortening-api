package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.report.*;

public interface ReportService {
    MyUrlReportDto getMyUrlReport(String adminUserName);

    MyPurchaseReportDto getMyPurchaseReport(String userName);

    AdminOverviewReportDto getAdminOverviewReport();

    AdminUserReportDto getAdminUserReport();

    AdminUrlReportDto getAdminUrlReport();
}
