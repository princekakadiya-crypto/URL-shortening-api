package com.tss.UrlShort.UrlShort.service;

import com.tss.UrlShort.UrlShort.dto.UserRegisterRequestDto;
import com.tss.UrlShort.UrlShort.dto.UserResponseDto;

public interface UserService {


        UserResponseDto registerUser(UserRegisterRequestDto request);

}
