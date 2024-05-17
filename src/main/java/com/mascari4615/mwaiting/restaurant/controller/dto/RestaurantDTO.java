package com.mascari4615.mwaiting.restaurant.controller.dto;

import com.mascari4615.mwaiting.user.repository.entity.User;
import com.mascari4615.mwaiting.restaurant.repository.entity.Restaurant;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestaurantDTO {
    private Long id;
    private User user;
    private String name;
    private String phoneNumber;
    private String address;
    private String time;
    private String description;

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setUser(restaurant.getUser());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setPhoneNumber(restaurant.getPhoneNumber());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setTime(restaurant.getTime());
        restaurantDTO.setDescription(restaurant.getDescription());
        return restaurantDTO;
    }
}
