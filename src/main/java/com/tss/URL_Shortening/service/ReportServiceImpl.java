package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.report.AdminOverviewReportDto;
import com.tss.URL_Shortening.dto.report.AdminUrlReportDto;
import com.tss.URL_Shortening.dto.report.AdminUserReportDto;
import com.tss.URL_Shortening.dto.report.MyUrlReportDto;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ShortUrlRepository shortUrlRepository;
    private final UserRepository userRepository;

    @Override
    public MyUrlReportDto getMyUrlReport(String adminUserName) {

        User user=userRepository.findByUserName(adminUserName)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        MyUrlReportDto reportDto=shortUrlRepository.getMyUrlReport(user.getUserId());

        return reportDto;
    }

    @Override
    public AdminOverviewReportDto getAdminOverviewReport() {
        AdminOverviewReportDto reportDto=shortUrlRepository.getAdminOverviewReport();

        return reportDto;
    }

    @Override
    public AdminUserReportDto getAdminUserReport() {
        AdminUserReportDto reportDto=shortUrlRepository.getAdminUserReport();
        return reportDto;
    }

    @Override
    public AdminUrlReportDto getAdminUrlReport() {
        AdminUrlReportDto reportDto=shortUrlRepository.getAdminUrlReport();
        return reportDto;
    }
}
