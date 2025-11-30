package com.example.ArticleManagerment.controller;

import com.example.ArticleManagerment.dto.reponse.ApiResponse;
import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.entity.Topic;
import com.example.ArticleManagerment.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/topics")
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;


    @PostMapping
    @PreAuthorize("hasAnyRole('Administrator', 'PR Manager')")
    public ApiResponse<TopicResponse> createTopic(@RequestBody TopicCreationReq request) {
        ApiResponse<TopicResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(topicService.createTopic(request));
        return apiResponse;
    }
}
