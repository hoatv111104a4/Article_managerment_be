package com.example.ArticleManagerment.Repository;

import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
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


    @Query(" SELECT u FROM User u "+
    "WHERE (:fullName IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))) " +
    "AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
    "AND (:role IS NULL OR u.role.id = :role) " +
    "AND (:status IS NULL OR u.status = :status) " +
    "AND (:fromDate IS NULL OR u.createdAt >= :fromDate) " +
    "AND (:toDate IS NULL OR u.createdAt <= :toDate)"
    )
    Page<User> findUsersByFilters(String fullName,
                                  String email,
                                  UUID role,
                                  Status status,
                                  LocalDateTime fromDate,
                                  LocalDateTime toDate,
                                  Pageable pageable);
}
