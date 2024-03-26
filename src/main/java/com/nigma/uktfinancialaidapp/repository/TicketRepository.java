package com.nigma.uktfinancialaidapp.repository;

import com.nigma.uktfinancialaidapp.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = """
    INSERT INTO t_ticket(id,total_ukt,campus_name,created_at,updated_at,aid_request,approval_status,aid_provided,ukt_fund_provided,approved_by,approved_at,user_id)
    VALUES (
        :#{#ticket.id},
        :#{#ticket.totalUkt},
        :#{#ticket.campusName},
        :#{#ticket.createdAt},
        :#{#ticket.updatedAt},
        :#{#ticket.aidRequest},
        :#{#ticket.aprovalStatus},
        :#{#ticket.aidProvided},
        :#{#ticket.uktFundProvided},
        :#{#ticket.approvedBy},
        :#{#ticket.approvedAt},
        :#{#ticket.user.id}
    )
"""
    )
    void insert(Ticket ticket);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
    value = """
    UPDATE t_ticket SET 
    updated_at = :#{#ticket.updatedAt},
    approval_status = :#{#ticket.aprovalStatus},
    aid_provided = :#{#ticket.aidProvided},
    ukt_fund_provided = :#{#ticket.uktFundProvided},
    approved_by = :#{#ticket.approvedBy},
    approved_at = :#{#ticket.approvedAt}
    WHERE id = :#{#ticket.id}
"""
    )
    void updateTicket(Ticket ticket);

    @Query(nativeQuery = true,
    value = "SELECT * FROM t_ticket WHERE id = :id")
    Optional<Ticket> findTicketById(String id);

    @Query(nativeQuery = true,
    value = "SELECT * FROM t_ticket"
    )
    List<Ticket> findAllTicket();
}
