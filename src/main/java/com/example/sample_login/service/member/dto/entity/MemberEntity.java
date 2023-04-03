package com.example.sample_login.service.member.dto.entity;

import com.example.sample_login.service.member.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


@Table(indexes = {
        @Index(columnList = "DT_INSERT"),
        @Index(columnList = "DT_UPDATE")
},
name = "MEMBER")
@Entity
public class MemberEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID_USER")
    private String idUser;
    @Column(name = "PW_USER" ,nullable = false,length = 100000)
    private String pwUser;
    @Column(name = "NM_USER")
    private String nmUser;
    @Column(name = "CD_ROLE")
    private String cdRole;

    @Column(name = "DT_INSERT")
    private String dtInsert;
    @Column(name = "DT_UPDATE")
    private String dtUpdate;

    @Builder
    public MemberEntity(String ID_USER, String PW_USER, String NM_USER, String CD_ROLE, String DT_INSERT, String DT_UPDATE) {
        this.idUser = ID_USER;
        this.pwUser = PW_USER;
        this.nmUser = NM_USER;
        this.cdRole = CD_ROLE;
        this.dtInsert = DT_INSERT;
        this.dtUpdate = DT_UPDATE;
    }

    public MemberDTO toMemberDTO(){
        return MemberDTO.builder().nmUser(this.nmUser)
                .pwUser(this.pwUser)
                .cdRole(this.cdRole)
                .idUser(this.idUser)
                .dtInsert(this.dtInsert)
                .dtUpdate(this.dtUpdate)
                .build();
    }

    protected MemberEntity() {

    }
}
