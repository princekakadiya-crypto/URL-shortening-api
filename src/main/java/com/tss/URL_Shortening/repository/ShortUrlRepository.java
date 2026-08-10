package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {
}
