package com.nigma.uktfinancialaidapp.repository;

import com.nigma.uktfinancialaidapp.model.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential,String> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM m_user_credential WHERE username = :username")
    Optional<UserCredential> findByUsername(String username);

    @Query(nativeQuery = true,
            value = "SELECT * FROM m_user_credential WHERE id = :id")
    Optional<UserCredential> findById(String id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = """
        INSERT INTO m_user_credential(id,username,password,is_active,role_id)
        VALUES (
            :#{#userCredential.id},
            :#{#userCredential.username},
            :#{#userCredential.password},
            :#{#userCredential.isActive},
            :#{#userCredential.role.id}
        )
"""
    )
    void insert(UserCredential userCredential);
}
