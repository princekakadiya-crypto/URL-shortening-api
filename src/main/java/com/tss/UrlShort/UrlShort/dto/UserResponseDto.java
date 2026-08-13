package com.tss.UrlShort.UrlShort.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDto {


        private Long id;

        private String fullName;

        private String email;

        private String mobile;

}
