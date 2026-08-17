package com.tss.URL_Shortening.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MyUrlReportDto {
    private long totalUrls;

    private long activeUrls;

    private long deletedUrls;

    private long totalVisits;

    private long totalVisitLimit;
}
