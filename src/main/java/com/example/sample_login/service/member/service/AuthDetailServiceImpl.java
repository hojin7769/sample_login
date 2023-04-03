package com.example.sample_login.service.member.service;

import com.example.sample_login.service.member.dto.MemberDTO;
import com.example.sample_login.service.member.dto.entity.MemberEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthDetailServiceImpl implements AuthDetailService{
    @Autowired
    private MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        MemberEntity member = memberService
        return memberService.findById(username);
    }
}
