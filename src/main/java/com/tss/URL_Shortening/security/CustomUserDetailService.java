package com.tss.URL_Shortening.security;

import com.tss.URL_Shortening.entity.User;
import com.tss.URL_Shortening.entity.Role;
import com.tss.URL_Shortening.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username).orElseThrow(
                ()->new RuntimeException("User Not Found")
        );

        Role role=user.getRole();

        Set<GrantedAuthority> authorities=new HashSet<>();

        SimpleGrantedAuthority authority=new SimpleGrantedAuthority(role.getRole().name());
        authorities.add(authority);

        return new org.springframework.security.core.userdetails.User(user.getUserName(),
                user.getPasswordHash(),authorities);

    }
}
