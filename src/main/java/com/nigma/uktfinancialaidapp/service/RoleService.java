package com.nigma.uktfinancialaidapp.service;

import com.nigma.uktfinancialaidapp.constant.ERole;
import com.nigma.uktfinancialaidapp.model.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
