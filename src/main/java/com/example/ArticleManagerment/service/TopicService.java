package com.example.ArticleManagerment.service;

import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.entity.Topic;

public interface TopicService {
    TopicResponse createTopic(TopicCreationReq request);
}
