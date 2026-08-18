package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.report.*;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.repository.PurchaseRepository;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ShortUrlRepository shortUrlRepository;
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;

    @Override
    public MyUrlReportDto getMyUrlReport(String adminUserName) {

        User user=userRepository.findByUserName(adminUserName)
                .orElseThrow(()->new ResourceNotFoundException("User Not Found"));

        MyUrlReportDto reportDto=shortUrlRepository.getMyUrlReport(user.getUserId());

        return reportDto;
    }

    @Override
    public MyPurchaseReportDto getMyPurchaseReport(String userName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return purchaseRepository.getMyPurchaseReport(user.getUserId());
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
