package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
