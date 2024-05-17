package com.mascari4615.mwaiting.user.service;

import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import com.mascari4615.mwaiting.user.controller.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    String register(UserRegisterDTO userRegisterDTO);

    // UserDTO login(UserDTO userDTO);

    List<UserDTO> findAll();

    UserDTO findById(Long id);
    UserDTO findByEmail(String email);
}
