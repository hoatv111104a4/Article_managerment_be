package com.example.ArticleManagerment.service;


import com.example.ArticleManagerment.dto.reponse.AuthenticationResponse;
import com.example.ArticleManagerment.dto.request.AuthenticationRequest;

public interface AuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);
}
