package com.example.ArticleManagerment.controller;

import com.example.ArticleManagerment.dto.reponse.ApiResponse;
import com.example.ArticleManagerment.dto.reponse.TopicResponse;
import com.example.ArticleManagerment.dto.request.TopicCreationReq;
import com.example.ArticleManagerment.dto.request.TopicUpdateReq;
import com.example.ArticleManagerment.entity.Topic;
import com.example.ArticleManagerment.service.TopicService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/topics")
@AllArgsConstructor
public class TopicController {
    private final TopicService topicService;


    @PostMapping
    @PreAuthorize("hasAnyRole('Administrator', 'PR Manager')")
    public ApiResponse<TopicResponse> createTopic(@Valid @RequestBody TopicCreationReq request) {
        ApiResponse<TopicResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(topicService.createTopic(request));
        return apiResponse;
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('Administrator', 'PR Manager')")
    public Page<TopicResponse> pageTopic(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime toDate
            ){
        String sortField = (sortBy != null &&  !sortBy.isEmpty())? sortBy : "createdAt";
        Pageable pageable = PageRequest.of(page,size,
                sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending()
                );
        return topicService.pageTopic(title, status, fromDate, toDate, pageable);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'PR Manager')")
    TopicResponse updateTopic(@PathVariable("id")UUID id, @RequestBody TopicUpdateReq request){
        return topicService.updateTopic(id, request);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('Administrator', 'PR Manager')")
    TopicResponse detailTopic(@PathVariable("id") UUID id){
        return topicService.detailTopic(id);
    }

    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasAnyRole('Administrator', 'PR Manager')")
    TopicResponse deactivateTopic(@PathVariable("id") UUID id ){
        return topicService.deactivateTopic(id);
    }


}
