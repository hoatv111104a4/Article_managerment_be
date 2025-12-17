package com.example.ArticleManagerment.dto.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WritingCampaignUpdateReq {

    private String name;

    private String description;

    private LocalDateTime registrationStart;

    private LocalDateTime registrationEnd;

    private LocalDateTime writingStart;

    private LocalDateTime writingEnd;


}
