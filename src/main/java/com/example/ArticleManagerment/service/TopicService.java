package com.example.ArticleManagerment.service;

import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.dto.request.TopicUpdateReq;
import com.example.ArticleManagerment.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TopicService {
    TopicResponse createTopic(TopicCreationReq request);

    Page<TopicResponse> pageTopic(String title, String status, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);

    TopicResponse updateTopic(UUID id, TopicUpdateReq request);

    TopicResponse detailTopic(UUID id);

    TopicResponse deactivateTopic(UUID id);
}
