package com.example.ArticleManagerment.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicCreationReq {

    @NotBlank(message = "MISSING_REQUIRED_FIELDS")
    private String title;

    @NotBlank(message = "MISSING_REQUIRED_FIELDS")
    private String description;

    @NotNull(message = "MISSING_REQUIRED_FIELDS")
    @Positive(message = "PRICE_MUST_BE_NUMERIC")
    private BigDecimal reward;

}
