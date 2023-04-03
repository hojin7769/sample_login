package com.example.sample_login.service.member.service.bridge;

import com.example.sample_login.common.annotation.Bridge;
import com.example.sample_login.service.member.dto.MemberDTO;
import com.example.sample_login.service.member.dto.entity.MemberEntity;
import com.example.sample_login.service.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Bridge
public class MemberBridge {
    private final MemberRepository repository;

    public MemberBridge(@Autowired MemberRepository repository) {
        this.repository = repository;
    }

    public List<MemberDTO> findMemberList() {
        return repository.findAll().stream().map(MemberEntity::toMemberDTO).collect(Collectors.toList());

    }

    public MemberDTO join(MemberDTO dto) {
        repository.save(dto.toEntity());
        return dto;
    }

    public MemberDTO selectID(String idMember) {
        try {
            MemberEntity member = repository.findById(idMember).get();
            return member.toMemberDTO();
        }catch (Exception e){
            return null;
        }
    }
}
