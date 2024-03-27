package com.nigma.uktfinancialaidapp.service;

import com.nigma.uktfinancialaidapp.model.entity.Ticket;
import com.nigma.uktfinancialaidapp.model.request.TicketRequest;
import com.nigma.uktfinancialaidapp.model.request.UpdateTicketRequest;
import com.nigma.uktfinancialaidapp.model.response.RejectedTicketResponse;
import com.nigma.uktfinancialaidapp.model.response.TicketResponse;

import java.util.List;

public interface TicketService {
    TicketResponse createTicket(TicketRequest request);
    TicketResponse getById(String id);
    List<TicketResponse> getAll();
    TicketResponse acceptTicket(UpdateTicketRequest request, String adminId);
    RejectedTicketResponse rejectTicket(UpdateTicketRequest request, String adminId);
}
