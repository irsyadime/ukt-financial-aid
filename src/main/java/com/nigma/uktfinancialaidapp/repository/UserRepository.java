package com.nigma.uktfinancialaidapp.repository;

import com.nigma.uktfinancialaidapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "INSERT INTO m_user(id,name,email,phone,user_credential_id) values (" +
            ":#{#user.id}, " +
            ":#{#user.name}, " +
            ":#{#user.email}, " +
            ":#{#user.phone}, " +
            ":#{#user.userCredential.id} " +
            ")"
    )
    void insert(User user);

    @Query(nativeQuery = true,
    value = "SELECT * FROM m_user WHERE id = :id"
    )
    Optional<User> findUserByid(String id);

    @Query(nativeQuery = true,
    value = "SELECT * FROM m_user"
    )
    List<User> findAllUser();

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE m_user SET " +
                    "name = :#{#user.name}, " +
                    "email = :#{#user.email}, " +
                    "phone = :#{#user.phone} " +
                    "WHERE id = :#{#user.id}")
    void updateUser(User user);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = "DELETE from m_user WHERE id = :id"
    )
    void deleteUserBydId(String id);
}
