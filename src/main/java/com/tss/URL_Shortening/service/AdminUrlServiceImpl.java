package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.enums.UrlStatus;
import com.tss.URL_Shortening.exception.InvalidOperationException;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.mapper.ShortUrlMapper;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminUrlServiceImpl implements AdminUrlService{


        private final UserRepository userRepository;
        private final ShortUrlRepository shortUrlRepository;

        // =========================================================
        // USER MANAGEMENT
        // =========================================================

        @Override
        public Page<User> getAllUsers(Pageable pageable) {

            return userRepository.findAll(pageable);
        }


        @Override
        public User getUserByUserId(Long id) {

            return userRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));
        }


        @Override
        @Transactional
        public void deleteUserById(Long id) {

            User user = userRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            userRepository.delete(user);
        }


        // =========================================================
        // SHORT URL MANAGEMENT
        // =========================================================

        @Override
        public Page<ShortUrlResponseDto> getAllShortUrls(Pageable pageable) {

            Page<ShortUrl> urls =
                    shortUrlRepository.findAll(pageable);

            return urls.map(url -> {

                String shortUrl =
                        "http://localhost:8080/"
                                + url.getShortCode();

                return new ShortUrlResponseDto(
                        url.getId(),
                        url.getOriginalUrl(),
                        shortUrl
                );
            });
        }


        @Override
        public ShortUrlResponseDto getShortUrlById(Long id) {

            ShortUrl url = shortUrlRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("URL not found"));

            String shortUrl =
                    "http://localhost:8080/"
                            + url.getShortCode();

            return new ShortUrlResponseDto(
                    url.getId(),
                    url.getOriginalUrl(),
                    shortUrl
            );
        }


        @Override
        @Transactional
        public void deleteShortUrl(Long id) {

            ShortUrl url = shortUrlRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("URL not found"));

            url.setStatus(UrlStatus.EXPIRED);

            shortUrlRepository.save(url);
        }


        @Override
        @Transactional
        public ShortUrlResponseDto restoreShortUrl(Long id) {

            ShortUrl url = shortUrlRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("URL not found"));

            url.setStatus(UrlStatus.ACTIVE);

            shortUrlRepository.save(url);

            String shortUrl =
                    "http://localhost:8080/"
                            + url.getShortCode();

            return new ShortUrlResponseDto(
                    url.getId(),
                    url.getOriginalUrl(),
                    shortUrl
            );
        }


}
