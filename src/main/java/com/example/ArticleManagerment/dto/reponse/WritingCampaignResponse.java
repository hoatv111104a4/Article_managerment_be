package com.example.ArticleManagerment.dto.reponse;

import com.example.ArticleManagerment.enums.CampaignStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WritingCampaignResponse {
    private UUID id;

    private String name;

    private String description;

    private LocalDateTime registrationStart;

    private LocalDateTime registrationEnd;

    private LocalDateTime writingStart;

    private LocalDateTime writingEnd;

    private Integer campaignNumber;

    private CampaignStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
