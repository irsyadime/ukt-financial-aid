package com.nigma.uktfinancialaidapp.service.impl;

import com.nigma.uktfinancialaidapp.constant.ERole;
import com.nigma.uktfinancialaidapp.model.entity.Role;
import com.nigma.uktfinancialaidapp.model.mapper.RoleMapper;
import com.nigma.uktfinancialaidapp.model.response.RoleResponse;
import com.nigma.uktfinancialaidapp.repository.RoleRepository;
import com.nigma.uktfinancialaidapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> currentRole = roleRepository.findByRoleName(role);
        if(!currentRole.isEmpty()){
            return currentRole.get();
        }
        Role newRole = Role.builder()
                .id(UUID.randomUUID().toString())
                .roleName(role)
                .build();
        return roleRepository.saveAndFlush(newRole);
    }
}
