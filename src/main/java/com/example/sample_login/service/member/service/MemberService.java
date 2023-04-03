package com.example.sample_login.service.member.service;

import com.example.sample_login.service.member.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    List<MemberDTO> findUserList();

    MemberDTO save(MemberDTO memberDTO);

    MemberDTO findById(String idUser);
}
