package com.example.ArticleManagerment.entity;

import com.example.ArticleManagerment.enums.CampaignStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "writing_campaigns")
public class WritingCampaign {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "registration_start")
    private LocalDateTime registrationStart;

    @Column(name = "registration_end")
    private LocalDateTime registrationEnd;

    @Column(name = "writing_start")
    private LocalDateTime writingStart;

    @Column(name = "writing_end")
    private LocalDateTime writingEnd;

    @Column(name = "campaign_number",insertable = false,updatable = false)
    private Integer campaignNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CampaignStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;
}
