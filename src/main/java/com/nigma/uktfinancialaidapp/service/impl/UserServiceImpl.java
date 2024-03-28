package com.nigma.uktfinancialaidapp.service.impl;

import com.nigma.uktfinancialaidapp.model.entity.User;
import com.nigma.uktfinancialaidapp.model.entity.UserCredential;
import com.nigma.uktfinancialaidapp.model.mapper.UserMapper;
import com.nigma.uktfinancialaidapp.model.request.UserRequest;
import com.nigma.uktfinancialaidapp.model.response.UserResponse;
import com.nigma.uktfinancialaidapp.repository.UserCredentialRepository;
import com.nigma.uktfinancialaidapp.repository.UserRepository;
import com.nigma.uktfinancialaidapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCredentialRepository userCredentialRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserResponse create(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id.toString());
        userRepository.insert(user);
        return UserMapper.convertToDTO(user);
    }

    @Override
    public UserResponse getById(String id) {
        User user = userRepository.findUserByid(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));
        return UserMapper.convertToDTO(user);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<User> users = userRepository.findAllUser();

        return users.stream().map(UserMapper::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public UserResponse update(UserRequest request) {
        User user = UserMapper.toEntity(getById(request.getId()));

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        userRepository.updateUser(user);
        return UserMapper.convertToDTO(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(String id) {
        User user = userRepository.findUserByid(id).orElse(null);
        UserCredential userCredential = userCredentialRepository.findById(user.getUserCredential().getId()).orElse(null);
        userCredential.setIsActive(false);
    }
}
