package com.example.sample_login.service.member.service;

import com.example.sample_login.service.member.dto.MemberDTO;
import com.example.sample_login.service.member.service.bridge.MemberBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberBridge bridge;

    public MemberServiceImpl(@Autowired MemberBridge bridge) {
        this.bridge = bridge;
    }
    @Override
    public List<MemberDTO> findUserList() {
        return bridge.findMemberList();
    }

    @Override
    public MemberDTO save(MemberDTO memberDTO) {
        return bridge.join(memberDTO);
    }

    @Override
    public MemberDTO findById(String idUser) {
        return bridge.selectID(idUser);
    }
}
