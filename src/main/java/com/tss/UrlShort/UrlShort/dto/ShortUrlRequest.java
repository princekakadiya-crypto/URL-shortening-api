package com.tss.UrlShort.UrlShort.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortUrlRequest {


        private String originalUrl;

        private Long userId;

}
