package com.nigma.uktfinancialaidapp.controller;

import com.nigma.uktfinancialaidapp.constant.AppPath;
import com.nigma.uktfinancialaidapp.model.request.AuthRequest;
import com.nigma.uktfinancialaidapp.model.response.CommonResponse;
import com.nigma.uktfinancialaidapp.model.response.LoginResponse;
import com.nigma.uktfinancialaidapp.model.response.RegisterResponse;
import com.nigma.uktfinancialaidapp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.AUTH)
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/mhs")
    public ResponseEntity<?> registerMhs(@RequestBody AuthRequest request){
        RegisterResponse response = authService.registerMhs(request);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success create new user")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody AuthRequest request){
        RegisterResponse response = authService.registerAdmin(request);
        CommonResponse<RegisterResponse> commonResponse = CommonResponse.<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success create new user")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request){
        LoginResponse response = authService.login(request);
        CommonResponse<LoginResponse> commonResponse = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success login")
                .data(response)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
