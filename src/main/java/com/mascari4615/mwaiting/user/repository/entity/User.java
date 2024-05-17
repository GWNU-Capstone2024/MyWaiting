package com.mascari4615.mwaiting.user.repository.entity;

import com.mascari4615.mwaiting.user.controller.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true) // unique 제약 조건 추가
    private String email; // 카멜 케이스로 만들어둬도, DB 들어갈때는 _들어감 (설정으로 변경 가능)

    @Column
    private String password;

    @Column
    private String role;

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        return user;
    }
}
