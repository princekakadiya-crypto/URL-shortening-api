package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.Image;
import com.tss.URL_Shortening.enums.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Optional<Image> findByUser_UserIdAndImageType(Long userId, ImageType imageType);
}
