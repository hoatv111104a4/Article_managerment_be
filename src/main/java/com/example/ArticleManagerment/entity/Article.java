package com.example.ArticleManagerment.entity;

import com.example.ArticleManagerment.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "articles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content",nullable = false)
    private String content;

    @Column(name = "submitted_at")
    private LocalDate submittedAt;

    @Column(name = "approved_at")
    private LocalDate approvedAt;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArticleStatus status;

    @Column(name = "cover_image")
    private String coverImage;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private ArticleAssignment articleAssignment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
