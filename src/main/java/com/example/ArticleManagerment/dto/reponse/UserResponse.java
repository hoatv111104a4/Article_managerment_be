package com.example.ArticleManagerment.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UUID id;

    private String fullName;

    private Integer gender;

    private LocalDate dateOfBirth;

    private String email;

    private String avatar;

    private String phone;

    private String password;

    private String refreshToken;

    private LocalDate createdAt;

    private String role;

    private String status;
}
