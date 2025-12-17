package com.example.ArticleManagerment.controller;

import com.example.ArticleManagerment.dto.reponse.ApiResponse;
import com.example.ArticleManagerment.dto.reponse.UserResponse;
import com.example.ArticleManagerment.dto.request.UserCreationReq;
import com.example.ArticleManagerment.dto.request.UserUpdateReq;
import com.example.ArticleManagerment.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('Administrator')")
    public ApiResponse<UserResponse> addUser(@RequestBody UserCreationReq request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.addUser(request));
        return apiResponse;
    }

    @GetMapping
    @PreAuthorize("hasRole('Administrator')")
    public Page<UserResponse> pageUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String fullName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) UUID role,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime toDate

            ){
        String sortField = (sortBy != null && !sortBy.isEmpty())? sortBy: "createdAt";
        Pageable pageable = PageRequest.of(page,size,sortDir.equalsIgnoreCase("asc")?
                Sort.by(sortField).ascending(): Sort.by(sortField).descending()
        );
        return userService.pageUser(fullName,email,role,status,fromDate,toDate,pageable);
    }

    @GetMapping("/my-profile")
    public ApiResponse<UserResponse> getMyInfo(){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getMyInfo());
        return apiResponse;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Administrator')")
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") UUID id){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.getUserById(id));
        return apiResponse;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('Administrator')")
    UserResponse updateUserById(@PathVariable("id") UUID id, @RequestBody UserUpdateReq request){
        return userService.updateUserById(id, request);
    }

    @PatchMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('Administrator')")
    UserResponse deactiveUserById(@PathVariable("id") UUID id){
        return userService.deactiveUserById(id);
    }


}
