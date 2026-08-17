package com.tss.URL_Shortening.dto.report;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminUserReportDto {
    private Long totalUsers;
    private Long activeUsers;
    private Long blockedUsers;
    private Long verifiedUsers;
    private Long unverifiedUsers;
}
