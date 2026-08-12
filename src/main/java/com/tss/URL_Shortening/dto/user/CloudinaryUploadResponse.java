package com.tss.URL_Shortening.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudinaryUploadResponse {
    private String imageUrl;
    private String publicId;
}
