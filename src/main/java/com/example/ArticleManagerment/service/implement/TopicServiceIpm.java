package com.example.ArticleManagerment.service.implement;

import com.example.ArticleManagerment.Repository.TopicRepository;
import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.dto.request.TopicUpdateReq;
import com.example.ArticleManagerment.entity.Topic;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import com.example.ArticleManagerment.exception.AppException;
import com.example.ArticleManagerment.exception.Errorcode;
import com.example.ArticleManagerment.mapper.TopicMapper;
import com.example.ArticleManagerment.service.TopicService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TopicServiceIpm implements TopicService {
    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;
    private final UserRepository userRepository;
    @Override
    public TopicResponse createTopic(TopicCreationReq request){
        Topic topic = topicMapper.toTopic(request);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        topic.setCreatedBy(user);
        topic.setStatus(Status.Active);
        Topic savedTopic = topicRepository.save(topic);
        return topicMapper.toTopicResponse(savedTopic);
    }

    @Override
    public Page<TopicResponse> pageTopic(String title, String status, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable){
        Status statusEnum = null;
        if (status != null && !status.isEmpty()){
            try {
                statusEnum = Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new AppException(Errorcode.INVALID_STATUS);
            }
        }
        return topicRepository.findTopicsByFilters(title, statusEnum, fromDate, toDate, pageable)
                .map(topicMapper::toTopicResponse);
    }

    @Override
    public TopicResponse updateTopic(UUID id, TopicUpdateReq request){
        Topic topic = topicRepository.findById(id).orElseThrow(()-> new AppException(Errorcode.TOPIC_NOT_FOUND));
        topicMapper.updateTopic(topic,request);
        return topicMapper.toTopicResponse(topicRepository.save(topic));
    }

    @Override
    public TopicResponse detailTopic(UUID id){
        return topicMapper.toTopicResponse(topicRepository.findById(id).orElseThrow(()-> new AppException(Errorcode.TOPIC_NOT_FOUND)));
    }

    @Override
    public TopicResponse deactivateTopic(UUID id){
        Topic topic = topicRepository.findById(id).orElseThrow(()-> new AppException(Errorcode.TOPIC_NOT_FOUND));
        topic.setStatus(Status.Inactive);
        topicRepository.save(topic);
        return topicMapper.toTopicResponse(topic);
    }
}
