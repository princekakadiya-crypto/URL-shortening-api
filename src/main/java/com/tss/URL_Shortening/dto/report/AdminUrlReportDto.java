package com.tss.URL_Shortening.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminUrlReportDto {
    private Long totalUrls;

    private Long activeUrls;
    private Long expiredUrls;
    private Long deletedUrls;

    private Long totalVisits;
    private Long totalVisitLimit;
}
