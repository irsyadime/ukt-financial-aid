package com.nigma.uktfinancialaidapp.model.entity;

import com.nigma.uktfinancialaidapp.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_role")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "role_name")
    private ERole roleName;
    @OneToMany(mappedBy = "role")
    private List<UserCredential> userCredentials;
}
