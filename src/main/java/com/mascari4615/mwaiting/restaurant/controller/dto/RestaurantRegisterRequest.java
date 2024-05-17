package com.mascari4615.mwaiting.restaurant.controller.dto;

import lombok.Data;

@Data

public class RestaurantRegisterRequest {
    private String name;
    private String phoneNumber;
    private String address;
    private String time;
    private String description;
}
