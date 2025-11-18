package com.example.ArticleManagerment.config;

import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.entity.Role;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
          if (userRepository.findByEmail("hoa573898@gmail.com").isEmpty()){
              Role adminRole = Role.builder()
                      .id(UUID.fromString("3017ED9E-8E98-4921-BC3B-972EEEAA2A5D"))
                      .build();
              User user = User.builder()
                      .role(adminRole)
                      .email("hoa573898@gmail.com")
                      .fullName("Admin")
                      .gender(1)
                      .dateOfBirth(LocalDate.parse("2005-01-01"))
                      .phone("0967606518")
                      .status(Status.Active)
                      .password(passwordEncoder.encode("Admin"))
                      .build();
              userRepository.save(user);
              System.out.println("Default admin user created with email: hoa573898@gmail.com and password: Admin");
          }
        };
    }
}
