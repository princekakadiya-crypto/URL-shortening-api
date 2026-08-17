package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.dto.user.CloudinaryUploadResponse;
import com.tss.URL_Shortening.dto.user.UpdateProfileRequestDto;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.entity.Image;
import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.enums.ImageType;
import com.tss.URL_Shortening.exception.ResourceNotFoundException;
import com.tss.URL_Shortening.mapper.UserMapper;
import com.tss.URL_Shortening.repository.ImageRepository;
import com.tss.URL_Shortening.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CloudinaryService cloudinaryService;
    private final ImageRepository imageRepository;



    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByUserName(name);
    }

    @Override
    @Transactional
    public UserResponseDto getMyProfile(String userName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateMyProfile(String userName, UpdateProfileRequestDto requestDto) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setUserName(requestDto.getUserName());
        user.setUpdatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional
    public UserResponseDto uploadProfilePicture(String userName, MultipartFile image) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Validate image
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image is required");
        }

        CloudinaryUploadResponse result = cloudinaryService.uploadImage(image);

        Image imageEntity = imageRepository
                .findByUser_UserIdAndImageType(user.getUserId(),ImageType.PROFILE_PICTURE)
                        .orElse(new Image());

        imageEntity.setImageUrl(result.getImageUrl());
        imageEntity.setImageType(ImageType.PROFILE_PICTURE);
        imageEntity.setStoragePublicId(result.getPublicId());
        imageEntity.setCreatedAt(LocalDateTime.now());
        imageEntity.setUser(user);

        imageRepository.save(imageEntity);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public void deleteProfilePicture(String userName) {

        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Image image = imageRepository
                .findByUser_UserIdAndImageType(user.getUserId(),ImageType.PROFILE_PICTURE)
                .orElseThrow(() -> new ResourceNotFoundException("Profile picture not found"));

        // Delete from Cloudinary/storage
        cloudinaryService.deleteImage(image.getImageUrl());

        // Delete DB record
        imageRepository.delete(image);
    }

}
