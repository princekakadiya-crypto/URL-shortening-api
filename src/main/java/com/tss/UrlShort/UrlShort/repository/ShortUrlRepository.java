package com.tss.UrlShort.UrlShort.repository;

import com.tss.UrlShort.UrlShort.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShortUrlRepository extends JpaRepository<Url,Long> {

        Optional<Url> findByShortCode(String shortCode);

        boolean existsByShortCode(String shortCode);

}
