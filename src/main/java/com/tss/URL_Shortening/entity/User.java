package com.tss.URL_Shortening.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name", nullable = false, length = 100)
    private String userName;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "mobile", unique = true, length = 20)
    private String mobile;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "email_verified", nullable = false)
    private Boolean emailVerified;

    @Column(name = "remaining_url_slots", nullable = false)
    private Integer remainingUrlSlots;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<ShortUrl> shortUrls = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Purchase> purchases = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Payment> payments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<OtpVerification> otpVerifications = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<TokenBlacklist> tokenBlacklists = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Image> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "updatedBy")
    private List<SystemConfig> systemConfigs = new ArrayList<>();
}
