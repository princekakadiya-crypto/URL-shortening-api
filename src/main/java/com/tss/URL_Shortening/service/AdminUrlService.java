package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUrlService {


    Page<User> getAllUsers(Pageable pageable);

    User getUserByUserId(Long id);

    void deleteUserById(Long id);


    Page<ShortUrlResponseDto> getAllShortUrls(Pageable pageable);

    ShortUrlResponseDto getShortUrlById(Long id);

    void deleteShortUrl(Long id);

    ShortUrlResponseDto restoreShortUrl(Long id);
}
