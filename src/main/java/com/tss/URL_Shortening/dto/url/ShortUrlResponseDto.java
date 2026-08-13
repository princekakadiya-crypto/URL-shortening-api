package com.tss.URL_Shortening.dto.url;

import com.tss.URL_Shortening.enums.UrlStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShortUrlResponseDto {

    private Long shortUrlId;

    private String longUrl;

    private String alias;

    private Integer totalVisits;

    private Integer remainingVisits;

    private UrlStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastAccessedAt;

    private Boolean isDeleted;

    private Long userId;
}
