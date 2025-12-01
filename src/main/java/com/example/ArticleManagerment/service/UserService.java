package com.example.ArticleManagerment.service;

import com.example.ArticleManagerment.dto.reponse.UserResponse;
import com.example.ArticleManagerment.dto.request.UserCreationReq;

public interface UserService {
    UserResponse addUser(UserCreationReq request);
}
