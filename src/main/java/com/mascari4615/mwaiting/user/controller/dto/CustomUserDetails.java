package com.mascari4615.mwaiting.user.controller.dto;

import com.mascari4615.mwaiting.user.repository.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 임의로 설정
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 임의로 설정
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 임의로 설정
    }

    @Override
    public boolean isEnabled() {
        return true; // 임의로 설정
    }
}
