package com.tss.UrlShort.UrlShort.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserRegisterRequestDto {


        private String fullName;

        private String email;

        private String mobile;

        private String password;

}
