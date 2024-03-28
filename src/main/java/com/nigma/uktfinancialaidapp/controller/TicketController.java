package com.nigma.uktfinancialaidapp.controller;

import com.nigma.uktfinancialaidapp.constant.AppPath;
import com.nigma.uktfinancialaidapp.model.entity.Ticket;
import com.nigma.uktfinancialaidapp.model.request.TicketRequest;
import com.nigma.uktfinancialaidapp.model.request.UpdateTicketRequest;
import com.nigma.uktfinancialaidapp.model.response.CommonResponse;
import com.nigma.uktfinancialaidapp.model.response.RejectedTicketResponse;
import com.nigma.uktfinancialaidapp.model.response.TicketResponse;
import com.nigma.uktfinancialaidapp.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AppPath.TICKET)
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody TicketRequest request){
        TicketResponse response = ticketService.createTicket(request);
        CommonResponse<TicketResponse> commonResponse = CommonResponse.<TicketResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success create ticket")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> approveTicket(@PathVariable String id, @RequestBody UpdateTicketRequest request){
        TicketResponse response = ticketService.acceptTicket(request,id);
        CommonResponse<TicketResponse> commonResponse = CommonResponse.<TicketResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Ticket approved")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<?> rejectTicket(@PathVariable String id, @RequestBody UpdateTicketRequest request){
        RejectedTicketResponse response = ticketService.rejectTicket(request,id);
        CommonResponse<RejectedTicketResponse> commonResponse = CommonResponse.<RejectedTicketResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Ticket rejected")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        TicketResponse response = ticketService.getById(id);
        CommonResponse<TicketResponse> commonResponse = CommonResponse.<TicketResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success get ticket")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<TicketResponse> responses = ticketService.getAll();
        CommonResponse<List<TicketResponse>> commonResponse = CommonResponse.<List<TicketResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all ticket")
                .data(responses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
