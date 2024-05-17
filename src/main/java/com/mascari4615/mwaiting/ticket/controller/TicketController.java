package com.mascari4615.mwaiting.ticket.controller;

import com.mascari4615.mwaiting.ticket.controller.DTO.TicketCreateDTO;
import com.mascari4615.mwaiting.ticket.controller.DTO.TicketDTO;
import com.mascari4615.mwaiting.ticket.repository.entity.TicketState;
import com.mascari4615.mwaiting.user.repository.UserRepository;
import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.RestaurantRepository;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import com.mascari4615.mwaiting.ticket.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TicketController {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final TicketService ticketService;

    @GetMapping("/ticket/{restaurantID}")
    public String createTicketForm(Model model, @PathVariable Long restaurantID) {
        System.out.println("createTicketForm");

        Optional<Restaurant> restaurantEntity = restaurantRepository.findById(restaurantID);
        Restaurant restaurant = restaurantEntity.get();
        System.out.println(restaurant);

        model.addAttribute("restaurant", restaurant);

        return "ticket-create";
    }

    @PostMapping("/ticket/create")
    public String createTicket(@ModelAttribute TicketCreateDTO ticketCreateDTO) {
        System.out.println("createTicket");

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userData = userRepository.findByEmail(email);
        User user = userData.get();
        System.out.println(user);

        Optional<Restaurant> restaurantData = restaurantRepository.findById(ticketCreateDTO.getRestaurantId());
        Restaurant restaurant = restaurantData.get();
        System.out.println(restaurant);

        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setRestaurant(restaurant);
        ticketDTO.setUser(user);
        ticketDTO.setDescription(ticketCreateDTO.getDescription());
        ticketDTO.setHeadCount(ticketCreateDTO.getHeadCount());

        ticketService.save(ticketDTO);

        return "redirect:/";
    }

    @GetMapping("/ticket/{ticketId}/reject")
    public String rejectTicket(@PathVariable Long ticketId) {
        TicketDTO ticketDTO = ticketService.findById(ticketId);
        ticketService.setState(ticketId, TicketState.REJECTED);
        return "redirect:/restaurant-home/" + ticketDTO.getRestaurant().getId();
    }
}
