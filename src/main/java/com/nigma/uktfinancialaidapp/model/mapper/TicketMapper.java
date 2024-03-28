package com.nigma.uktfinancialaidapp.model.mapper;

import com.nigma.uktfinancialaidapp.model.entity.Ticket;
import com.nigma.uktfinancialaidapp.model.response.TicketResponse;

public class TicketMapper {
    public static TicketResponse convertToDTO(Ticket ticket){
        return TicketResponse.builder()
                .id(ticket.getId())
                .totalUkt(ticket.getTotalUkt())
                .campusName(ticket.getCampusName())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .aidRequest(ticket.getAidRequest())
                .aidProvided(ticket.getAidProvided())
                .aprovalStatus(ticket.getAprovalStatus())
                .uktFundProvided(ticket.getUktFundProvided())
                .approvedBy(ticket.getApprovedBy())
                .approvedAt(ticket.getApprovedAt())
                .build();
    }
}
