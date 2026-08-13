package com.tss.UrlShort.UrlShort.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String fullName;

        @Column(unique = true, nullable = false)
        private String email;

        @Column(unique = true, nullable = false)
        private String mobile;

        @Column(nullable = false)
        private String password;

        @Column(nullable = false)

        private Integer linkSlots = 3;
}
