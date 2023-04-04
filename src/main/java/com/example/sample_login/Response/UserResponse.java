package com.example.sample_login.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String idUser;
    private String nmUser;
    private String pwUser;
    private String cdRole;
    private String accessToken;
    private String refreshToken;


}
