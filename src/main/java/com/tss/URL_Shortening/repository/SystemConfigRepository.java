package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.SystemConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, Long> {
    Optional<SystemConfig> findByConfigKey(String configKey);

    Page<SystemConfig> findAllByOrderByConfigKeyAsc(Pageable pageable);
}
