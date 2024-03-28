package com.nigma.uktfinancialaidapp.service;

import com.nigma.uktfinancialaidapp.model.request.AuthRequest;
import com.nigma.uktfinancialaidapp.model.response.LoginResponse;
import com.nigma.uktfinancialaidapp.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerMhs(AuthRequest request);

    RegisterResponse registerAdmin(AuthRequest request);

    LoginResponse login(AuthRequest request);
}
