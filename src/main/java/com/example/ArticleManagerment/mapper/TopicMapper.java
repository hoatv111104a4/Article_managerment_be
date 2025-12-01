package com.example.ArticleManagerment.mapper;

import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.dto.request.TopicUpdateReq;
import com.example.ArticleManagerment.entity.Topic;
import com.example.ArticleManagerment.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic toTopic(TopicCreationReq request);
    TopicResponse toTopicResponse(Topic topic);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void updateTopic(@MappingTarget Topic topic, TopicUpdateReq request);

    default void updateStatus(@MappingTarget Topic topic, String status){
        if (status != null){
            topic.setStatus(Status.valueOf(status));
        }
    }


}
