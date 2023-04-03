package com.example.sample_login.service.member.dto;

import com.example.sample_login.service.member.dto.entity.MemberEntity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDTO implements UserDetails {

    @Id
    @Setter private String idUser;
    @Setter private String pwUser;
    @Setter private String nmUser;
    @Setter private String cdRole;
    private String dtInsert;
    private String dtUpdate;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .ID_USER(this.idUser)
                .NM_USER(this.nmUser)
                .PW_USER(this.pwUser)
                .CD_ROLE("user")
                .DT_INSERT(LocalDateTime.now().toString())
                .DT_UPDATE(this.dtUpdate)
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE" + "user"));
    }

    @Override
    public String getPassword() {
        return this.pwUser;
    }

    @Override
    public String getUsername() {
        return this.idUser;
    }

    public String getUserRealName() {
        return this.nmUser;
    }

    public String getUserRole() {
        return cdRole;
    }


    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }
}
