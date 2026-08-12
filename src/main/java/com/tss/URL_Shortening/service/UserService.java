package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.user.UpdateProfileRequestDto;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDto getMyProfile(String userName);

    UserResponseDto updateMyProfile(String userName, UpdateProfileRequestDto requestDto);

    UserResponseDto uploadProfilePicture(String userName, MultipartFile image);

    void deleteProfilePicture(String userName);
}
