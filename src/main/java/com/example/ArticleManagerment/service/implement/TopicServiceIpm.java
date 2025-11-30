package com.example.ArticleManagerment.service.implement;

import com.example.ArticleManagerment.Repository.TopicRepository;
import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.entity.Topic;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import com.example.ArticleManagerment.mapper.TopicMapper;
import com.example.ArticleManagerment.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
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

}
