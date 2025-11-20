package com.example.ArticleManagerment.Repository;

import com.example.ArticleManagerment.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken,String> {
}
