package com.example.ArticleManagerment.mapper;

import com.example.ArticleManagerment.dto.reponse.WritingCampaignResponse;
import com.example.ArticleManagerment.dto.request.WritingCampaignCreationReq;
import com.example.ArticleManagerment.dto.request.WritingCampaignUpdateReq;
import com.example.ArticleManagerment.entity.WritingCampaign;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WritingCampaignMapper {


    WritingCampaign toWritingCampaign(WritingCampaignCreationReq request);

    WritingCampaignResponse toWritingCampaignResponse(WritingCampaign writingCampaign);

    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "createdBy",ignore = true)
    @Mapping(target = "campaignNumber",ignore = true)
    void updateWritingCampaign(@MappingTarget WritingCampaign writingCampaign, WritingCampaignUpdateReq request);
}
