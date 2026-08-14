package com.tss.URL_Shortening.mapper;

import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
}
