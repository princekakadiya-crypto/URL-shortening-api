package com.tss.URL_Shortening.controller;

import com.tss.URL_Shortening.dto.user.UpdateProfileRequestDto;
import com.tss.URL_Shortening.dto.user.UserResponseDto;
import com.tss.URL_Shortening.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getMyProfile(Authentication authentication) {
        String userName = authentication.getName();

        return ResponseEntity.ok(
                userService.getMyProfile(userName)
        );
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateMyProfile(@Valid @RequestBody UpdateProfileRequestDto requestDto, Authentication authentication) {

        String userName = authentication.getName();

        return ResponseEntity.ok(
                userService.updateMyProfile(userName, requestDto)
        );
    }

    @PostMapping(value = "/me/profile-picture", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> uploadProfilePicture(@RequestParam("image") MultipartFile image, Authentication authentication) {

        String userName = authentication.getName();

        return ResponseEntity.ok(
                userService.uploadProfilePicture(userName, image)
        );
    }

    @DeleteMapping("/me/profile-picture")
    public ResponseEntity<Void> deleteProfilePicture(
            Authentication authentication) {

        String userName = authentication.getName();

        userService.deleteProfilePicture(userName);

        return ResponseEntity.noContent().build();
    }

}

