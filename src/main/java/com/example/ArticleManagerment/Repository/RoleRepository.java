package com.example.ArticleManagerment.Repository;

import com.example.ArticleManagerment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role,UUID> {
}
