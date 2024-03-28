package com.nigma.uktfinancialaidapp.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_user")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email",unique = true)
    private String email;
    @Column(name = "phone",unique = true)
    private String phone;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}
