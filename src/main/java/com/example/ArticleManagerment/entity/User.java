package com.example.ArticleManagerment.entity;

import com.example.ArticleManagerment.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
