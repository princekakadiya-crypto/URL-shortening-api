package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
}
