package com.mascari4615.mwaiting.user.controller.dto;

import com.mascari4615.mwaiting.user.repository.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRegisterDTO {
    private String email;
    private String password;
}
