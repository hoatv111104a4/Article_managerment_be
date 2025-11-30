package com.example.ArticleManagerment.Repository;

import com.example.ArticleManagerment.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
}
