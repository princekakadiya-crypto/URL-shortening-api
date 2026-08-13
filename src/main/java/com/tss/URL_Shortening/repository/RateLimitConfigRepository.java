package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.RateLimitConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateLimitConfigRepository extends JpaRepository<RateLimitConfig, Long> {
    Optional<RateLimitConfig> findByEndpointKey(String endpointKey);
}
