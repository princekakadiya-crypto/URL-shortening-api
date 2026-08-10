package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.TokenBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, Long> {
}
