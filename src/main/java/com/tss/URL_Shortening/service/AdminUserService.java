package com.tss.URL_Shortening.service;

import com.tss.URL_Shortening.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminUserService {



    Page<User> getAllUsers(Pageable pageable);

    User getUserByUserId(Long id);

    void deleteUserById(Long id);

}
