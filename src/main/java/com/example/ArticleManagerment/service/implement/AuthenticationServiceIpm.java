package com.example.ArticleManagerment.service.implement;

import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.dto.reponse.AuthenticationResponse;
import com.example.ArticleManagerment.dto.request.AuthenticationRequest;
import com.example.ArticleManagerment.entity.Role;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import com.example.ArticleManagerment.exception.AppException;
import com.example.ArticleManagerment.exception.Errorcode;
import com.example.ArticleManagerment.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AuthenticationServiceIpm implements AuthenticationService {
    UserRepository userRepository;


    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    @NonFinal
    @Value("${jwt.valid-duration}")
    protected long VALID_DURATION;

    @NonFinal
    @Value("${jwt.refreshable-duration}")
    protected long REFRESHABLE_DURATION;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                ()-> new RuntimeException("User not found")
        );
        if (user.getStatus().equals(Status.Active)){
            return AuthenticationResponse.builder()
                    .authenticated(false)
                    .build();
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(),user.getPassword());
        if (!authenticated) throw new AppException(Errorcode.UNAUTHENTICATED);
        var token = generateToken(user);
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(User user){
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(VALID_DURATION, ChronoUnit.HOURS).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope",buildScope(user))
                .claim("userId",user.getId())
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512),jwtClaimsSet);
        try {
            signedJWT.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return signedJWT.serialize();
        }catch (JOSEException e){
            log.error("Error while sign token: {}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        Role role = user.getRole();
        if (role !=null){
            stringJoiner.add("ROLE_"+role.getName());

        }
        return stringJoiner.toString();
    }

}
