package com.tss.URL_Shortening.repository;

import com.tss.URL_Shortening.entity.Role;
import com.tss.URL_Shortening.entity.ShortUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}
