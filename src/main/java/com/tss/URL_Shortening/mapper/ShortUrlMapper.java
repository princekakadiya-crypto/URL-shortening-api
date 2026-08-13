package com.tss.URL_Shortening.mapper;

import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShortUrlMapper {
    ShortUrlResponseDto toDto(ShortUrl shortUrl);
}
