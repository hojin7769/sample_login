package com.example.sample_login.api;

import com.example.sample_login.Response.BasicResponse;
import com.example.sample_login.Response.UserResponse;
import com.example.sample_login.jwt.JwtTokenProvider;
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

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;

    public MemberController(@Autowired MemberService memberService, @Autowired AuthenticationManager authenticationManager
    , @Autowired JwtTokenProvider tokenProvider) {
        this.memberService = memberService;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }
    @PostMapping("/list")
    public List<MemberDTO> findMemberList(){
        return memberService.findUserList();
    }

    @PostMapping("/join")
    public MemberDTO join(@RequestBody MemberDTO dto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        dto.setPwUser(passwordEncoder.encode(dto.getPwUser()));
        memberService.save(dto);
        return dto;

//        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<BasicResponse> login(@RequestBody MemberDTO memberDTO){
        String idUser = memberDTO.getIdUser();
        String pwUser = memberDTO.getPwUser();
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(idUser, pwUser));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        MemberDTO dto = (MemberDTO) authentication.getPrincipal();

        String accessToken = tokenProvider.createAccressToken(dto.getIdUser());
        String refreshToken = tokenProvider.createRefresh(dto.getIdUser());

        UserResponse userResponse = new UserResponse(dto.getIdUser(), dto.getNmUser(), dto.getPwUser(), dto.getCdRole(), accessToken, refreshToken);

        BasicResponse basicResponse = BasicResponse.builder().code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .result(Collections.singletonList(userResponse))
                .build();

        return new ResponseEntity<>(basicResponse, basicResponse.getHttpStatus());
    }
}
