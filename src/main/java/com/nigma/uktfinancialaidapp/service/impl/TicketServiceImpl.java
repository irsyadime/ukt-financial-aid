package com.nigma.uktfinancialaidapp.service.impl;

import com.nigma.uktfinancialaidapp.constant.EAprovalStatus;
import com.nigma.uktfinancialaidapp.model.entity.Ticket;
import com.nigma.uktfinancialaidapp.model.entity.User;
import com.nigma.uktfinancialaidapp.model.mapper.TicketMapper;
import com.nigma.uktfinancialaidapp.model.mapper.UserMapper;
import com.nigma.uktfinancialaidapp.model.request.TicketRequest;
import com.nigma.uktfinancialaidapp.model.request.UpdateTicketRequest;
import com.nigma.uktfinancialaidapp.model.response.RejectedTicketResponse;
import com.nigma.uktfinancialaidapp.model.response.TicketResponse;
import com.nigma.uktfinancialaidapp.model.response.UserResponse;
import com.nigma.uktfinancialaidapp.repository.TicketRepository;
import com.nigma.uktfinancialaidapp.service.TicketService;
import com.nigma.uktfinancialaidapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TicketResponse createTicket(TicketRequest request) {
        UUID id = UUID.randomUUID();
        User user = UserMapper.toEntity(userService.getById(request.getUserId()));
        Ticket ticket = Ticket.builder()
                .id(id.toString())
                .createdAt(LocalDateTime.now())
                .totalUkt(request.getTotalUKT())
                .campusName(request.getCampusName())
                .aidRequest(request.getAidRequest())
                .aprovalStatus(EAprovalStatus.PENDING)
                .user(user)
                .build();
        ticketRepository.insert(ticket);
        return TicketResponse.builder()
                .id(ticket.getId())
                .totalUkt(ticket.getTotalUkt())
                .campusName(ticket.getCampusName())
                .createdAt(ticket.getCreatedAt())
                .aidRequest(ticket.getAidRequest())
                .build();
    }

    @Override
    public TicketResponse getById(String id) {
        Ticket ticket = ticketRepository.findTicketById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"ticket not found"));
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

    @Override
    public List<TicketResponse> getAll() {
        List<Ticket> tickets = ticketRepository.findAllTicket();
        return tickets.stream().map(TicketMapper::convertToDTO).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TicketResponse acceptTicket(UpdateTicketRequest request, String adminId) {
        Ticket ticket = ticketRepository.findTicketById(request.getId()).orElse(null);
        UserResponse user = userService.getById(adminId);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setAprovalStatus(EAprovalStatus.APPROVED);
        ticket.setApprovedBy(user.getEmail());
        ticket.setAidProvided(request.getAidProvided());
        ticket.setApprovedAt(LocalDateTime.now());
        ticket.setUktFundProvided(request.getUktFundProvided());
        ticketRepository.updateTicket(ticket);
        return TicketMapper.convertToDTO(ticket);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public RejectedTicketResponse rejectTicket(UpdateTicketRequest request, String adminId) {
        Ticket ticket = ticketRepository.findTicketById(request.getId()).orElse(null);
        UserResponse user = userService.getById(adminId);
        ticket.setUpdatedAt(LocalDateTime.now());
        ticket.setAprovalStatus(EAprovalStatus.REJECTED);
        ticketRepository.updateTicket(ticket);
        return RejectedTicketResponse.builder()
                .id(ticket.getId())
                .totalUkt(ticket.getTotalUkt())
                .campusName(ticket.getCampusName())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .aidRequest(ticket.getAidRequest())
                .aprovalStatus(ticket.getAprovalStatus())
                .rejectedBy(user.getEmail())
                .rejectedAt(LocalDateTime.now())
                .build();
    }
}
