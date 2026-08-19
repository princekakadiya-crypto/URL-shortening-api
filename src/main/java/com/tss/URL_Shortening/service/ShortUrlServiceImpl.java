package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.cache.SystemConfigCache;
import com.tss.URL_Shortening.dto.url.CreateShortUrlRequestDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.enums.UrlStatus;
import com.tss.URL_Shortening.repository.ShortUrlRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ShortUrlServiceImpl implements ShortUrlService{


        private final ShortUrlRepository shortUrlRepository;
        private final UserRepository userRepository;
        private final SystemConfigCache systemConfigCache;


        // =========================================================
        // GET SHORT URL
        // =========================================================

        @Override
        public ShortUrl getShortUrl(String shortCode) {

            return shortUrlRepository.findByShortCode(shortCode)
                    .orElseThrow(() ->
                            new RuntimeException("Short URL not found"));
        }

        @Override
        public ShortUrl getUrlById(Long id) {

            return shortUrlRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("URL not found"));
        }


        // =========================================================
        // CREATE SHORT URL
        // =========================================================

        @Override
        @Transactional
        public ShortUrlResponseDto shortenUrl(
                CreateShortUrlRequestDto request) {


            // -----------------------------------------------------
            // 1. Find User
            // -----------------------------------------------------

            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));


            // -----------------------------------------------------
            // 2. Check Link Slots
            // -----------------------------------------------------

            if (user.getLinkSlots() == null ||
                    user.getLinkSlots() <= 0) {

                throw new RuntimeException(
                        "No link slots available. " +
                                "Please purchase additional link slots."
                );
            }


            // -----------------------------------------------------
            // 3. Generate Short Code
            // -----------------------------------------------------

            String shortCode = generateShortCode();


            // -----------------------------------------------------
            // 4. Create URL
            // -----------------------------------------------------

            ShortUrl shortUrl = new ShortUrl();

            shortUrl.setOriginalUrl(request.getOriginalUrl());
            shortUrl.setShortCode(shortCode);
            shortUrl.setUser(user);
            shortUrl.setVisitsLimit(systemConfigCache.getInt("DEFAULT_VISITOR_LIMIT"));


            // -----------------------------------------------------
            // 5. Save URL
            // -----------------------------------------------------

            ShortUrl savedUrl =
                    shortUrlRepository.save(shortUrl);


            // -----------------------------------------------------
            // 6. Decrease User's Link Slot
            // -----------------------------------------------------

            user.setLinkSlots(
                    user.getLinkSlots() - 1
            );

            userRepository.save(user);


            // -----------------------------------------------------
            // 7. Create Short URL
            // -----------------------------------------------------

            String shortUrlString =
                    "http://localhost:8080/api/urls/"
                            + savedUrl.getShortCode();


            // -----------------------------------------------------
            // 8. Return Response
            // -----------------------------------------------------

            return new ShortUrlResponseDto(
                    savedUrl.getId(),
                    savedUrl.getOriginalUrl(),
                    shortUrlString
            );
        }


        // =========================================================
        // GENERATE SHORT CODE
        // =========================================================

        private String generateShortCode() {

            return UUID.randomUUID()
                    .toString()
                    .replace("-", "")
                    .substring(0, 6);
        }





        @Override
        @Transactional
        public void recordVisit(ShortUrl url) {


            // -----------------------------------------------------
            // 1. Check if URL is already expired
            // -----------------------------------------------------

            if (url.getStatus() == UrlStatus.EXPIRED) {

                throw new RuntimeException(
                        "This link has expired."
                );
            }


            // -----------------------------------------------------
            // 2. Check remaining visitor limit
            // -----------------------------------------------------

            if (url.getVisitsLimit() == null ||
                    url.getVisitsLimit() <= 0) {

                url.setStatus(
                        UrlStatus.EXPIRED
                );

                shortUrlRepository.save(url);

                throw new RuntimeException(
                        "This link has expired."
                );
            }


            // -----------------------------------------------------
            // 3. Increase total visit count
            // -----------------------------------------------------

            int currentVisits =
                    url.getVisitsCount() == null
                            ? 0
                            : url.getVisitsCount();

            url.setVisitsCount(
                    currentVisits + 1
            );


            // -----------------------------------------------------
            // 4. Decrease remaining visitor limit
            // -----------------------------------------------------

            url.setVisitsLimit(
                    url.getVisitsLimit() - 1
            );


            // -----------------------------------------------------
            // 5. If limit reaches 0, expire the URL
            // -----------------------------------------------------

            if (url.getVisitsLimit() == 0) {

                url.setStatus(
                        UrlStatus.EXPIRED
                );
            }


            // -----------------------------------------------------
            // 6. Save updated URL
            // -----------------------------------------------------

            shortUrlRepository.save(url);
        }




}
