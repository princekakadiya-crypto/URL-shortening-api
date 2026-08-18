package com.tss.URL_Shortening.mapper;

import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "profilePictureUrl",source = "image.imageUrl")
    UserResponseDto toDto(User user);
}
