package com.mascari4615.mwaiting.ticket.service;

import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;

import java.util.List;

public interface TicketService {
    void save(TicketDTO ticketDTO);

    List<TicketDTO> findAll();

    TicketDTO findById(Long id);

    void setState(Long id, TicketState state);
}
