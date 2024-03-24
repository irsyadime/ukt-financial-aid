package com.nigma.uktfinancialaidapp.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nigma.uktfinancialaidapp.constant.EAidType;
import com.nigma.uktfinancialaidapp.constant.EAprovalStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_ticket")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "total_ukt")
    private Long totalUkt;
    @Column(name = "campus_name")
    private String campusName;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "aid_request")
    private EAidType aidRequest;
    @Column(name = "approval_status")
    private EAprovalStatus aprovalStatus;
    @Column(name = "aid_provided")
    private EAidType aidProvided;
    @Column(name = "ukt_fund_provided")
    private Long uktFundProvided;
    @Column(name = "approved_by")
    private String approvedBy;
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
