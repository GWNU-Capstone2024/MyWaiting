package com.gwnu2024.mywaiting.member.service;

import com.gwnu2024.mywaiting.member.controller.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    void save(MemberDTO memberDTO);

    MemberDTO login(MemberDTO memberDTO);

    List<MemberDTO> findAll();

    MemberDTO findById(Long id);
}
