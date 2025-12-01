package com.example.ArticleManagerment.service.implement;

import com.example.ArticleManagerment.Repository.RoleRepository;
import com.example.ArticleManagerment.Repository.UserRepository;
import com.example.ArticleManagerment.dto.reponse.UserResponse;
import com.example.ArticleManagerment.dto.request.UserCreationReq;
import com.example.ArticleManagerment.entity.Role;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import com.example.ArticleManagerment.exception.AppException;
import com.example.ArticleManagerment.exception.Errorcode;
import com.example.ArticleManagerment.mapper.UserMapper;
import com.example.ArticleManagerment.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
