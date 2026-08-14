package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.url.CreateShortUrlRequestDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;

public interface ShortUrlService {





        ShortUrlResponseDto shortenUrl(CreateShortUrlRequestDto request);

        ShortUrl getShortUrl(String shortCode);


        void recordVisit(
                ShortUrl url);

        ShortUrl getUrlById(Long id);



}
