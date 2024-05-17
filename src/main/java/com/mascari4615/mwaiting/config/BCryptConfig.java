package com.mascari4615.mwaiting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BCryptConfig {

    // TODO: 공부
    // Spring Security는 사용자 인증(로그인)시 비밀번호에 대해서 단방향 해시 암호화를 진행해서 저장되어 있는 비밀번호와 대조한다.
    // 따라서 회원가입 시 비밀번호 항목에 대해서 암호화를 진행해야 한다.

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}