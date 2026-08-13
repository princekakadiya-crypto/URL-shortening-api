package com.tss.UrlShort.UrlShort.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShortUrlResponseDto {


        private Long id;

        private String originalUrl;

        private String shortUrl;

}
