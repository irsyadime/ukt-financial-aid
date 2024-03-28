package com.nigma.uktfinancialaidapp.model.mapper;

import com.nigma.uktfinancialaidapp.model.entity.User;
import com.nigma.uktfinancialaidapp.model.response.UserResponse;

public class UserMapper {
    public static UserResponse convertToDTO(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .build();
    }

    public static User toEntity(UserResponse userResponse){
        return User.builder()
                .id(userResponse.getId())
                .name(userResponse.getName())
                .email(userResponse.getEmail())
                .phone(userResponse.getPhone())
                .build();
    }
}
