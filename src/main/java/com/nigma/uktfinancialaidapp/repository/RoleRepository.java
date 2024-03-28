package com.nigma.uktfinancialaidapp.repository;

import com.nigma.uktfinancialaidapp.constant.ERole;
import com.nigma.uktfinancialaidapp.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByRoleName(ERole role);

}
