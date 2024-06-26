package com.nigma.uktfinancialaidapp.service.impl;

import com.nigma.uktfinancialaidapp.constant.ERole;
import com.nigma.uktfinancialaidapp.model.entity.AppUser;
import com.nigma.uktfinancialaidapp.model.entity.Role;
import com.nigma.uktfinancialaidapp.model.entity.User;
import com.nigma.uktfinancialaidapp.model.entity.UserCredential;
import com.nigma.uktfinancialaidapp.model.request.AuthRequest;
import com.nigma.uktfinancialaidapp.model.response.LoginResponse;
import com.nigma.uktfinancialaidapp.model.response.RegisterResponse;
import com.nigma.uktfinancialaidapp.repository.UserCredentialRepository;
import com.nigma.uktfinancialaidapp.security.JwtUtil;
import com.nigma.uktfinancialaidapp.service.AuthService;
import com.nigma.uktfinancialaidapp.service.RoleService;
import com.nigma.uktfinancialaidapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserCredentialRepository userCredentialRepository;
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerMhs(AuthRequest request) {
        Role role = roleService.getOrSave(ERole.ROLE_MAHASISWA);
        UserCredential userCredential = UserCredential.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .isActive(true)
                .build();
        userCredentialRepository.insert(userCredential);
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .userCredential(userCredential)
                .build();
        userService.create(user);
        return RegisterResponse.builder()
                .username(userCredential.getUsername())
                .role(userCredential.getRole().getRoleName())
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerAdmin(AuthRequest request) {
        Role role = roleService.getOrSave(ERole.ROLE_ADMIN);
        UserCredential userCredential = UserCredential.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .isActive(true)
                .build();
        userCredentialRepository.insert(userCredential);
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .userCredential(userCredential)
                .build();
        userService.create(user);
        return RegisterResponse.builder()
                .username(userCredential.getUsername())
                .role(userCredential.getRole().getRoleName())
                .build();
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername().toLowerCase(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser appUser = (AppUser) authentication.getPrincipal();
        String token = jwtUtil.generateToken(appUser);
        return LoginResponse.builder()
                .token(token)
                .role(appUser.getRole())
                .build();
    }
}
