package com.nigma.uktfinancialaidapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
}
