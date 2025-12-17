package com.example.ArticleManagerment.service.implement;

import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.Repository.WritingCampaignRepo;
import com.example.ArticleManagerment.dto.reponse.WritingCampaignResponse;
import com.example.ArticleManagerment.dto.request.WritingCampaignCreationReq;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.entity.WritingCampaign;
import com.example.ArticleManagerment.enums.CampaignStatus;
import com.example.ArticleManagerment.exception.AppException;
import com.example.ArticleManagerment.exception.Errorcode;
import com.example.ArticleManagerment.mapper.WritingCampaignMapper;
import com.example.ArticleManagerment.service.WritingCampaignService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritingCampaignServiceIpm implements WritingCampaignService {
    private final WritingCampaignRepo writingCampaignRepo;
    private final WritingCampaignMapper writingCampaignMapper;
    private final UserRepository userRepository;
    @Override
    public WritingCampaignResponse createWritingCampaign(WritingCampaignCreationReq request){
        validateWritingCampaignTime(request);
        WritingCampaign writingCampaign = writingCampaignMapper.toWritingCampaign(request);

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
        writingCampaign.setCreatedBy(user);
        writingCampaign.setStatus(CampaignStatus.Closed);
        WritingCampaign savedWritingCampaign = writingCampaignRepo.save(writingCampaign);
        return writingCampaignMapper.toWritingCampaignResponse(savedWritingCampaign);
    }

    private void validateWritingCampaignTime(WritingCampaignCreationReq request) {

        if (request.getWritingEnd().isBefore(request.getWritingStart())) {
            throw new AppException(Errorcode.INVALID_WRITING_DATE_RANGE);
        }

        if (request.getRegistrationEnd().isBefore(request.getRegistrationStart())) {
            throw new AppException(Errorcode.INVALID_REGISTRATION_END);
        }

        if (request.getRegistrationEnd().isAfter(request.getWritingEnd())) {
            throw new AppException(Errorcode.INVALID_REGISTRATION_END);
        }

        if (request.getRegistrationStart().isAfter(request.getWritingStart())) {
            throw new AppException(Errorcode.INVALID_REGISTRATION_START);
        }
    }

}
