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
public class UpdateTicketRequest {
    private String ticketId;
    private EAidType aidProvided;
    private Long uktFundProvided;
}
