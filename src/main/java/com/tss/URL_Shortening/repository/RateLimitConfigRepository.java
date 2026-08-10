package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.RateLimitConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateLimitConfigRepository extends JpaRepository<RateLimitConfig, Long> {
}
