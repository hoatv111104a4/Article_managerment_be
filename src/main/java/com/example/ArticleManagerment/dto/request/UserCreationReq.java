package com.example.ArticleManagerment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreationReq {

    @NotBlank(message = "MISSING_REQUIRED_FIELDS")
    private String fullName;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private Integer gender;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private LocalDate dateOfBirth;

    @NotBlank(message = "MISSING_REQUIRED_FIELDS")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "EMAIL_INVALID"
    )
    private String email;

    private String avatar;

    @Pattern(
            regexp = "^(0[3|5|7|8|9][0-9]{8}|\\+84[3|5|7|8|9][0-9]{8})$",
            message = "PHONE_INVALID"
    )
    private String phone;

    @NotBlank(message = "MISSING_REQUIRED_FIELDS")
    private String password;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private UUID roleId;
}

