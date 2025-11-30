package com.example.ArticleManagerment.mapper;

import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.dto.request.TopicUpdateReq;
import com.example.ArticleManagerment.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic toTopic(TopicCreationReq request);
    TopicResponse toTopicResponse(Topic topic);
    void updateTopic(@MappingTarget Topic topic, TopicUpdateReq request);
}
