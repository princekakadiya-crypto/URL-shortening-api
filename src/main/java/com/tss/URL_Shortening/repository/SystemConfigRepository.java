package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {
}
