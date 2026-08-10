package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
