package com.example.ArticleManagerment.controller;

import com.example.ArticleManagerment.dto.reponse.ApiResponse;
import com.example.ArticleManagerment.dto.reponse.WritingCampaignResponse;
import com.example.ArticleManagerment.dto.request.WritingCampaignCreationReq;
import com.example.ArticleManagerment.service.WritingCampaignService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/writing-campaigns")
@AllArgsConstructor
public class WritingCampaignController {
    private final WritingCampaignService writingCampaignService;

    @PostMapping
    @PreAuthorize("hasRole('Administrator')")
    public ApiResponse<WritingCampaignResponse> createWritingCampaign(@Valid @RequestBody WritingCampaignCreationReq request){
        ApiResponse<WritingCampaignResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(writingCampaignService.createWritingCampaign(request));
        return apiResponse;
    }
}
