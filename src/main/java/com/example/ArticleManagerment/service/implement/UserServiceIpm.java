package com.example.ArticleManagerment.service.implement;

import com.example.ArticleManagerment.Repository.RoleRepository;
import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.dto.reponse.UserResponse;
import com.example.ArticleManagerment.dto.request.UserCreationReq;
import com.example.ArticleManagerment.dto.request.UserUpdateReq;
import com.example.ArticleManagerment.entity.Role;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import com.example.ArticleManagerment.exception.AppException;
import com.example.ArticleManagerment.exception.Errorcode;
import com.example.ArticleManagerment.mapper.UserMapper;
import com.example.ArticleManagerment.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceIpm implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public UserResponse addUser(UserCreationReq request){
        if (userRepository.existsByEmail(request.getEmail())) throw new AppException(Errorcode.EMAIL_EXISTED);
        if (userRepository.existsByPhone(request.getPhone())) throw new AppException(Errorcode.PHONE_EXISTED);
        User user = userMapper.toUser(request);
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(()-> new RuntimeException("Chức vụ không tồn tại"));
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(Status.Active);
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public Page<UserResponse> pageUser(String fullName, String email, UUID role, String status, LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable){
        Status statusEnum = null;
        if (status !=null && !status.isEmpty()){
            try {
                statusEnum = Status.valueOf(status);
            } catch (IllegalArgumentException e) {
                throw new AppException(Errorcode.INVALID_STATUS);
            }
        }

        return userRepository.findUsersByFilters(fullName,email,role,statusEnum,fromDate,toDate,pageable).map(userMapper::toUserResponse);
    }

    @Override
    public UserResponse getMyInfo(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(()-> new AppException(Errorcode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse getUserById(UUID id){
        User user = userRepository.findById(id).orElseThrow(()-> new AppException(Errorcode.USER_NOT_EXISTED));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(UUID id, UserUpdateReq request){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User không tồn tại"));
        userMapper.updateUser(user,request);
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);

    }

    @Override
    public UserResponse deactiveUserById(UUID id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User không tồn tại"));
        user.setStatus(Status.Inactive);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}
