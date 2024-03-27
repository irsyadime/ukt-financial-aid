package com.nigma.uktfinancialaidapp.service;

import com.nigma.uktfinancialaidapp.model.entity.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    AppUser loadUserByUserId(String id);
}
