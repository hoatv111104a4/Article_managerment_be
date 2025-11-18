package com.example.ArticleManagerment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article_assignments")
public class ArticleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "assigned_articles_count")
    private Integer assignedArticlesCount;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @CreationTimestamp
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @CreationTimestamp
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "campaign_id", nullable = false)
    private WritingCampaign writingCampaign;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "sub_campaign_id", nullable = false)
    private SubCampaign subCampaign;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
}
