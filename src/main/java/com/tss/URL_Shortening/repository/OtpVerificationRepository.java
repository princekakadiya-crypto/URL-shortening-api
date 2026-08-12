package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
    @Query(value = """
    SELECT *
    FROM otp_verifications
    WHERE user_id = :userId
      AND purpose = :purpose
      AND verified_at IS NULL
      AND expires_at > CURRENT_TIMESTAMP
      AND attempts < max_attempts
    ORDER BY created_at DESC
    LIMIT 1
    """, nativeQuery = true)
    Optional<OtpVerification> findValidLatestOtp(
            @Param("userId") Long userId,
            @Param("purpose") String purpose
    );
}
