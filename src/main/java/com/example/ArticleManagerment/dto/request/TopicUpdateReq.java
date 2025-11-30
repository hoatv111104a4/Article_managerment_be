package com.example.ArticleManagerment.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicUpdateReq {
    private String title;
    private String description;
    private Double reward;
}
