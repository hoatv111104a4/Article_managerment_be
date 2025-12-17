package com.example.ArticleManagerment.mapper;

import com.example.ArticleManagerment.dto.reponse.UserResponse;
import com.example.ArticleManagerment.dto.request.UserCreationReq;
import com.example.ArticleManagerment.dto.request.UserUpdateReq;
import com.example.ArticleManagerment.entity.Role;
import com.example.ArticleManagerment.entity.User;
import com.example.ArticleManagerment.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "roleId", qualifiedByName = "mapRole")
    User toUser(UserCreationReq request);

    @Mapping(target = "role", source = "role" , qualifiedByName = "mapRoleToString")
    UserResponse toUserResponse(User user);

    @Mapping(target = "role",source = "roleId",qualifiedByName = "mapRole")
    void updateUser(@MappingTarget User user , UserUpdateReq request);

    default void updateStatus(@MappingTarget User user, String status){
        if (status!=null){
            user.setStatus(Status.valueOf(status));
        }
    }

    @Named("mapRole")
    default Role mapRoleIdToRole(UUID roleId) {
        if (roleId == null) return null;
        Role role = new Role();
        role.setId(roleId);
        return role;
    }

    @Named("mapRoleToString")
    default String mapRoleToString(Role role){
        if (role == null){
            return null;
        }
        return role.getName();
    }





}
