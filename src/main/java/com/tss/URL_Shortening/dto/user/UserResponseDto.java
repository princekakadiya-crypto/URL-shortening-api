package com.tss.URL_Shortening.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private String userName;

    private String email;

    private Integer remainingUrlSlots;

    private String profilePictureUrl;

    private LocalDateTime updatedAt;
}
