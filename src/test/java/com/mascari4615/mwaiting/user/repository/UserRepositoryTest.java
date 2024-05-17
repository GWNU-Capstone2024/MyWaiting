package com.mascari4615.mwaiting.user.repository;

import com.mascari4615.mwaiting.user.repository.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void crudTest() {
        User user = User.builder()
                .id(0L)
                .email("test@gmail.com")
                .password("test")
                .build();

        // create test
        userRepository.save(user);

        //get test
        User foundUser = userRepository.findById(1L).get();
    }
}
