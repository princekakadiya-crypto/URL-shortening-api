package com.tss.URL_Shortening.dto.url;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateShortUrlRequestDto {

    private String originalUrl;
}
