package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "profile_name", nullable = false)
    private String profile_name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "date_of_birth", nullable = false)
    private Date birthDate;
    @Column(name = "verified")
    private Boolean verified;
}
