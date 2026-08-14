package com.tss.URL_Shortening.dto.url;

import com.tss.URL_Shortening.enums.UrlStatus;
import lombok.*;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class ShortUrlResponseDto {




        private Long id;

        private String originalUrl;

        private String shortUrl;



}
