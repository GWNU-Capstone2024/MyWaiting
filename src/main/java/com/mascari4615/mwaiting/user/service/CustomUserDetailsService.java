package com.mascari4615.mwaiting.user.service;

import com.mascari4615.mwaiting.user.controller.dto.CustomUserDetails;
import com.mascari4615.mwaiting.user.repository.UserRepository;
import com.mascari4615.mwaiting.user.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("email = " + email);
        Optional<User> userData = userRepository.findByEmail(email);

        if (userData.isPresent())
        {
            User user = userData.get();
            return new CustomUserDetails(user);
        }

        return null;
    }
}
