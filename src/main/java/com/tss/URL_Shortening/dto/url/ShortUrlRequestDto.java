package com.tss.URL_Shortening.dto.url;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShortUrlRequestDto {


        private String originalUrl;

        private Long userId;



}
