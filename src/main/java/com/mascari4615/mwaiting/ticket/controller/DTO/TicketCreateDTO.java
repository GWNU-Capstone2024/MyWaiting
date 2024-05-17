package com.mascari4615.mwaiting.ticket.controller.DTO;

import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.repository.entity.Ticket;
import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.user.repository.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TicketCreateDTO {
    private Long restaurantId;
    private Long headCount;
    private String description;
}
