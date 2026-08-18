package com.tss.URL_Shortening.mapper;

import com.tss.URL_Shortening.dto.url.ShortUrlResponseDto;
import com.tss.URL_Shortening.entity.ShortUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ShortUrlMapper {
    @Mapping(source = "shortCode",target = "shortUrl")
    ShortUrlResponseDto toDto(ShortUrl shortUrl);
}
