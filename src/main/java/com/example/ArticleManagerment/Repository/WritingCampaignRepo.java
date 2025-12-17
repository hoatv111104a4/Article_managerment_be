package com.example.ArticleManagerment.Repository;

import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.entity.WritingCampaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WritingCampaignRepo extends JpaRepository<WritingCampaign, UUID> {
}
