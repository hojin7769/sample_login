package com.example.sample_login.api;

import com.example.sample_login.service.member.dto.MemberDTO;
import com.example.sample_login.service.member.dto.entity.MemberEntity;
import com.example.sample_login.service.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;



    private final AuthenticationManager authenticationManager;

    public MemberController(@Autowired MemberService memberService, @Autowired AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping("/list")
    public List<MemberDTO> findMemberList(){
        return memberService.findUserList();
    }

    @PostMapping("/join")
    public MemberDTO join(@RequestBody MemberDTO dto){
        System.out.println(dto.getIdUser());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        dto.setPwUser(passwordEncoder.encode(dto.getPwUser()));
        memberService.save(dto);
        return dto;

//        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDTO> login(@RequestBody MemberDTO memberDTO){
        String idUser = memberDTO.getIdUser();
        String pwUser = memberDTO.getPwUser();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(idUser, pwUser));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberDTO dto = (MemberDTO) authentication.getPrincipal();
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}
