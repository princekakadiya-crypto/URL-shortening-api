package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.PageDto;
import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminUrlService {



        PageDto<ShortUrlResponseDto> getAllUrls(Pageable pageable);

        ShortUrlResponseDto getUrlById(Long id);

        void deleteUrl(Long id);

        ShortUrlResponseDto restoreUrl(Long id);

}
