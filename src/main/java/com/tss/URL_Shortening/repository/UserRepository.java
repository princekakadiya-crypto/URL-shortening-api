package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
