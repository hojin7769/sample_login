package com.example.sample_login.service.member.repository;

import com.example.sample_login.service.member.dto.MemberDTO;
import com.example.sample_login.service.member.dto.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity,String> {
}
