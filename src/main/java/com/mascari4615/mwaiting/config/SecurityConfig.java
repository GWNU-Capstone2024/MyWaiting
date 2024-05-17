package com.mascari4615.mwaiting.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Configuration 클래스로 등록
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 위에서부터 순서대로

        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/css/**", "/error").permitAll()
                .requestMatchers("/user/register", "/user/login").permitAll()
                .requestMatchers("/admin").hasRole("ADMIN")
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()); // 나머지 경로에 대한 처리

        // permitAll (모든 사용자가 로그인하지 않아도 접근가능)
        // authenticated (모든 사용자가 로그인했다면 접근가능)
        // hasRole
        // denyAll (모든 사용자가 거부)

        // 와일드카드 **

        http.formLogin((auth) -> auth
                .loginPage("/user/login") // 로그인 페이지 리다이렉션
                .loginProcessingUrl("/user/login") // TODO: 공부
                .permitAll()
        );

        // http.csrf((auth) -> auth.disable());

        // https://substantial-park-a17.notion.site/10-36136f5a91f647b499dbcb5a884aff72
        // 세션에 관해 세션의 소멸 시간, 아이디당 세션 생성 개수를 설정하는 방법
        http.sessionManagement((auth) -> auth
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true));

        // 세션 고정 보호 TODO: 공부
        http.sessionManagement((auth) -> auth
                .sessionFixation().changeSessionId());

        return http.build();
    }
}