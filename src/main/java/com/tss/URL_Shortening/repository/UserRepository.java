package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);

    Optional<User> findByUserName(String userName);

    boolean existsByUserNameAndUserIdNot(String userName, Long userId);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
