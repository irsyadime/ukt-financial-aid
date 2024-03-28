package com.nigma.uktfinancialaidapp.model.response;

import com.nigma.uktfinancialaidapp.constant.EAidType;
import com.nigma.uktfinancialaidapp.constant.EAprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RejectedTicketResponse {
    private String id;
    private Long totalUkt;
    private String campusName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private EAidType aidRequest;
    private EAprovalStatus aprovalStatus;
    private EAidType aidProvided;
    private Long uktFundProvided;
    private String rejectedBy;
    private LocalDateTime rejectedAt;
}
