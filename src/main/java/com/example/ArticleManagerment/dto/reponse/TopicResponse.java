package com.example.ArticleManagerment.dto.reponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicResponse {
    private UUID id;
    private String title;
    private String description;
    private Double reward;
    private String status;
    private LocalDateTime createdAt;
}
