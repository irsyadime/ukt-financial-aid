package com.nigma.uktfinancialaidapp.service;

import com.nigma.uktfinancialaidapp.model.entity.User;
import com.nigma.uktfinancialaidapp.model.request.UserRequest;
import com.nigma.uktfinancialaidapp.model.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(User user);
    UserResponse getById(String id);
    List<UserResponse> getAllUser();
    UserResponse update(UserRequest request);
    void delete(String id);
}
