package com.tss.UrlShort.UrlShort.service;

import com.tss.UrlShort.UrlShort.dto.ShortUrlRequest;
import com.tss.UrlShort.UrlShort.dto.ShortUrlResponseDto;
import com.tss.UrlShort.UrlShort.entity.Url;

public interface ShortUrlService {


        ShortUrlResponseDto shortenUrl(ShortUrlRequest request);

        Url getShortUrl(String shortCode);


        void recordVisit(
                Url url);

        Url getUrlById(Long id);

}
