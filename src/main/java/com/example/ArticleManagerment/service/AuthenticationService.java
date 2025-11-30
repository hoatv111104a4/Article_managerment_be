package com.example.ArticleManagerment.service;


import com.example.ArticleManagerment.dto.reponse.AuthenticationResponse;
import com.example.ArticleManagerment.dto.reponse.IntrospectResponse;
import com.example.ArticleManagerment.dto.request.AuthenticationRequest;
import com.example.ArticleManagerment.dto.request.IntrospectRequest;
import com.example.ArticleManagerment.dto.request.LogoutRequest;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    void logout(LogoutRequest request) throws ParseException,JOSEException;
}
