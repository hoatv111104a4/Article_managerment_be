package com.example.ArticleManagerment.Repository;

import com.example.ArticleManagerment.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(@NotBlank(message = "MISSING_REQUIRED_FIELDS") @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "EMAIL_INVALID"
    ) String email);

    boolean existsByPhone(@Pattern(
            regexp = "^(0[3|5|7|8|9][0-9]{8}|\\+84[3|5|7|8|9][0-9]{8})$",
            message = "PHONE_INVALID"
    ) String phone);
}
