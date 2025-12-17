package com.example.ArticleManagerment.service;

import com.example.ArticleManagerment.dto.reponse.WritingCampaignResponse;
import com.example.ArticleManagerment.dto.request.WritingCampaignCreationReq;

public interface WritingCampaignService {
    WritingCampaignResponse createWritingCampaign(WritingCampaignCreationReq request);
}
