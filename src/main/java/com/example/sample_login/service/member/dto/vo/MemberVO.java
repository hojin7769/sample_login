package com.example.sample_login.service.member.dto.vo;

import org.w3c.dom.css.RGBColor;

import java.util.Date;
import java.util.Objects;

public class MemberVO {
    private final String ID_USER;
    private final String PW_USER;
    private final String NM_USER;
    private final String CD_ROLE;
    private final String DT_INSERT;
    private final String DT_UPDATE;

    public MemberVO(String idUser, String pwUser, String nmUser, String cdRole) {
        ID_USER = idUser;
        PW_USER = pwUser;
        NM_USER = nmUser;
        CD_ROLE = cdRole;
        DT_INSERT = new Date().toString();
        DT_UPDATE = new Date().toString();
    }


    public static MemberVO of(String idUser, String pwUser, String nmUser, String cdRole){
        return new MemberVO(idUser, pwUser, nmUser,cdRole);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberVO memberVO = (MemberVO) o;
        return ID_USER.equals(memberVO.ID_USER) && PW_USER.equals(memberVO.PW_USER) && NM_USER.equals(memberVO.NM_USER) && CD_ROLE.equals(memberVO.CD_ROLE);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID_USER, PW_USER, NM_USER, CD_ROLE);
    }
}
