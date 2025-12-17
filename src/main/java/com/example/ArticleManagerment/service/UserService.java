package com.example.ArticleManagerment.service;

import com.example.ArticleManagerment.dto.reponse.UserResponse;
import com.example.ArticleManagerment.dto.request.UserCreationReq;
import com.example.ArticleManagerment.dto.request.UserUpdateReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UserService {
    UserResponse addUser(UserCreationReq request);

    Page<UserResponse> pageUser(String fullName, String email, UUID role, String status, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    UserResponse getMyInfo();

    UserResponse getUserById(UUID id);

    UserResponse updateUserById(UUID id, UserUpdateReq request);

    UserResponse deactiveUserById(UUID id);
}
