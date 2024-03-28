package com.nigma.uktfinancialaidapp.service.impl;

import com.nigma.uktfinancialaidapp.model.entity.AppUser;
import com.nigma.uktfinancialaidapp.model.entity.UserCredential;
import com.nigma.uktfinancialaidapp.repository.UserCredentialRepository;
import com.nigma.uktfinancialaidapp.service.AppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final UserCredentialRepository userCredentialRepository;

    @Override
    public AppUser loadUserByUserId(String id) {
        UserCredential userCredential =userCredentialRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Invalid credential"));
        return getAppUser(userCredential);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCredential userCredential = userCredentialRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid credential"));
        return getAppUser(userCredential);
    }

    private static AppUser getAppUser(UserCredential userCredential){
        return AppUser.builder()
                .id(userCredential.getId())
                .username(userCredential.getUsername())
                .password(userCredential.getPassword())
                .role(userCredential.getRole().getRoleName())
                .build();
    }
}
