package com.example.ArticleManagerment.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WritingCampaignCreationReq {

    @NotBlank(message = "MISSING_REQUIRED_FIELDS")
    private String name;

    private String description;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private LocalDateTime registrationStart;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private LocalDateTime registrationEnd;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private LocalDateTime writingStart;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    private LocalDateTime writingEnd;


}
