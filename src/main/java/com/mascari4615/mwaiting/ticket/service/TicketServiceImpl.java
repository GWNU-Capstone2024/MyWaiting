package com.mascari4615.mwaiting.ticket.service;

import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.ticket.repository.TicketRepository;
import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public void save(TicketDTO ticketDTO) {
        Ticket ticket = Ticket.builder()
                .user(ticketDTO.getUser())
                .restaurant(ticketDTO.getRestaurant())
                .number(100L)
                .state(TicketState.WAITING)
                .headCount(ticketDTO.getHeadCount())
                .description(ticketDTO.getDescription())
                .build();

        ticketRepository.save(ticket); // 이건 이름 save 메소드여야 함
    }

    @Override
    public List<TicketDTO> findAll() {
        List<Ticket> ticketList = ticketRepository.findAll();
        List<TicketDTO> ticketDTOList = new ArrayList<>();
        for (Ticket ticket : ticketList) {
            ticketDTOList.add(TicketDTO.toTicketDTO(ticket));
        }
        return ticketDTOList;
    }

    @Override
    public TicketDTO findById(Long id)
    {
        Optional<Ticket> ticketData = ticketRepository.findById(id);
        if (ticketData.isPresent()) {
            return TicketDTO.toTicketDTO(ticketData.get());
        }
        return null;
    }

    @Override
    public void setState(Long id, TicketState state) {
        System.out.println(state);
        Optional<Ticket> ticketData = ticketRepository.findById(id);
        if (ticketData.isPresent()) {
            Ticket ticket = ticketData.get();
            ticket.setState(state);
            ticketRepository.save(ticket);
        }
    }
}
