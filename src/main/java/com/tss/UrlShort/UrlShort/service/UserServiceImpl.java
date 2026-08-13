package com.tss.UrlShort.UrlShort.service;


import com.tss.UrlShort.UrlShort.dto.UserRegisterRequestDto;
import com.tss.UrlShort.UrlShort.dto.UserResponseDto;
import com.tss.UrlShort.UrlShort.entity.User;
import com.tss.UrlShort.UrlShort.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements  UserService {

        private final UserRepository userRepository;

        @Override
        public UserResponseDto registerUser(UserRegisterRequestDto request) {

            User user = new User();

            user.setFullName(request.getFullName());
            user.setEmail(request.getEmail());
            user.setMobile(request.getMobile());
            user.setPassword(request.getPassword());

            User savedUser = userRepository.save(user);

            return new UserResponseDto(
                    savedUser.getId(),
                    savedUser.getFullName(),
                    savedUser.getEmail(),
                    savedUser.getMobile()
            );
        }

}
