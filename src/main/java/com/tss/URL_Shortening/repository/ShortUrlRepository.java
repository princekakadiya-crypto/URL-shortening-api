package com.tss.URL_Shortening.repository;

import com.cloudinary.Url;
import com.tss.URL_Shortening.entity.ShortUrl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortUrlRepository extends JpaRepository<ShortUrl, Long> {

        Optional<ShortUrl> findByShortCode(String shortCode);

        boolean existsByShortCode(String shortCode);



}
