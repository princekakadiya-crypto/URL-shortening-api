package com.tss.URL_Shortening.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roleId;

    @Column
    private com.tss.URL_Shortening.enums.Role role;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}