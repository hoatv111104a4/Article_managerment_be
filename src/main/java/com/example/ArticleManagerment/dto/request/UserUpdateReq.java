package com.example.ArticleManagerment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateReq {
    private String fullName;
    private Integer gender;
    private LocalDate dateOfBirth;
    private String email;
    private String avatar;
    private String phone;
    private UUID roleId;

}
