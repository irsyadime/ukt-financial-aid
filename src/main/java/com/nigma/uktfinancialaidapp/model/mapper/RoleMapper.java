package com.nigma.uktfinancialaidapp.model.mapper;

import com.nigma.uktfinancialaidapp.model.entity.Role;
import com.nigma.uktfinancialaidapp.model.response.RoleResponse;

public class RoleMapper {
    public static RoleResponse convertToDTO(Role role){
        return RoleResponse.builder()
                .id(role.getId())
                .role(role.getRoleName())
                .build();
    }
}
