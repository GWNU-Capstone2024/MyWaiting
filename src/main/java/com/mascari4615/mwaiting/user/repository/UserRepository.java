package com.mascari4615.mwaiting.user.repository;

import com.mascari4615.mwaiting.user.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // email으로 회원 정보 조회 (select * from users where email=?)
    // Optional null방지
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
