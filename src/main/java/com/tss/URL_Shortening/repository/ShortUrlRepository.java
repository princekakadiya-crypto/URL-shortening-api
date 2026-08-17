package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.dto.report.AdminOverviewReportDto;
import com.tss.URL_Shortening.dto.report.AdminUrlReportDto;
import com.tss.URL_Shortening.dto.report.AdminUserReportDto;
import com.tss.URL_Shortening.dto.report.MyUrlReportDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
    Optional<ShortUrl> findByShortCode(String shortCode);

    boolean existsByShortCode(String shortCode);


    Page<ShortUrl> findAllByDeletedFalse(Pageable pageable);

    @Query(value = """
    SELECT
        COUNT(*) AS totalUrls,
        COUNT(*) FILTER (WHERE is_deleted = false) AS activeUrls,
        COUNT(*) FILTER (WHERE is_deleted = true) AS deletedUrls,
        COALESCE(SUM(visits_count), 0) AS totalVisits,
        COALESCE(SUM(visits_limit), 0) AS totalVisitLimit
    FROM short_urls
    WHERE user_id = :userId
    """, nativeQuery = true)
    MyUrlReportDto getMyUrlReport(Long userId);

    @Query(value = """
    SELECT
        (SELECT COUNT(*)
         FROM users) AS totalUsers,

        (SELECT COUNT(*)
         FROM users
         WHERE is_active = true) AS activeUsers,

        (SELECT COUNT(*)
         FROM users
         WHERE is_active = false) AS blockedUsers,

        (SELECT COUNT(*)
         FROM short_urls) AS totalUrls,

        (SELECT COUNT(*)
         FROM short_urls
         WHERE is_deleted = false) AS activeUrls,

        (SELECT COUNT(*)
         FROM short_urls
         WHERE is_deleted = true) AS deletedUrls,

        (SELECT COALESCE(SUM(visits_count), 0)
         FROM short_urls) AS totalVisits
    """, nativeQuery = true)
    AdminOverviewReportDto getAdminOverviewReport();

    @Query(value = """
    SELECT
        COUNT(*) AS totalUsers,

        COUNT(*) FILTER (
            WHERE is_active = true
        ) AS activeUsers,

        COUNT(*) FILTER (
            WHERE is_active = false
        ) AS blockedUsers,

        COUNT(*) FILTER (
            WHERE email_verified = true
        ) AS verifiedUsers,

        COUNT(*) FILTER (
            WHERE email_verified = false
        ) AS unverifiedUsers

    FROM users
    """, nativeQuery = true)
    AdminUserReportDto getAdminUserReport();

    @Query(value = """
    SELECT
        COUNT(*) AS totalUrls,

        COUNT(*) FILTER (
            WHERE status = 'ACTIVE'
        ) AS activeUrls,

        COUNT(*) FILTER (
            WHERE status = 'EXPIRED'
        ) AS expiredUrls,

        COUNT(*) FILTER (
            WHERE status = 'DELETED'
        ) AS deletedUrls,

        COALESCE(SUM(visits_count), 0) AS totalVisits,

        COALESCE(SUM(visits_limit), 0) AS totalVisitLimit

    FROM short_urls
    """, nativeQuery = true)
    AdminUrlReportDto getAdminUrlReport();
}
