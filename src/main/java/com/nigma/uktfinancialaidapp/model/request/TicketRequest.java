package com.nigma.uktfinancialaidapp.model.request;

import com.nigma.uktfinancialaidapp.constant.EAidType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String userId;
    private EAidType aidRequest;
    private Long totalUKT;
    private String campusName;
}
