package com.example.ArticleManagerment.Repository;

import aj.org.objectweb.asm.commons.Remapper;
import com.example.ArticleManagerment.entity.Topic;
import com.example.ArticleManagerment.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
    @Query("SELECT t FROM Topic t " +
            "WHERE (:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND (:fromDate IS NULL OR t.createdAt >= :fromDate) " +
            "AND (:toDate IS NULL OR t.createdAt <= :toDate)")
    Page<Topic> findTopicsByFilters(
            @Param("title") String title,
            @Param("status") Status status,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            Pageable pageable
    );}
