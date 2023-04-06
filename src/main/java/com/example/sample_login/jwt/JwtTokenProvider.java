package com.example.sample_login.jwt;

import com.example.sample_login.service.member.service.AuthDetailService;
import com.example.sample_login.service.member.service.MemberService;
import com.example.sample_login.util.redis.RedisUtil;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;
import java.util.List;

@Configuration
public class JwtTokenProvider {

    @Value("${app.jwt.secret}")
    private String secretKey;
    @Value("${app.jwt.expire-timeout}")
    private Long accessExpiration;
    @Value("${app.jwt.refresh-timeout}")
    private Long refreshExpiration;


    private final RedisUtil redisUtil;

    private final AuthDetailService detailService;

    public JwtTokenProvider( @Autowired RedisUtil redisUtil , @Autowired AuthDetailService detailService) {
        this.redisUtil = redisUtil;
        this.detailService = detailService;
    }

    public String createAccressToken(String userId){
        Long tokenInvalidTime = accessExpiration * 60 * 1000L;
        String accessToken = createToken(userId,tokenInvalidTime);
        redisUtil.setValues(accessToken, userId, Duration.ofMillis(tokenInvalidTime));
        return accessToken;
    }

    public String createRefresh(String userId){
        Long tokenInvalidTime = refreshExpiration * 60 * 1000L;
        String refreshToken = createToken(userId,tokenInvalidTime);
        redisUtil.setValues(refreshToken, userId, Duration.ofMillis(tokenInvalidTime));
        return refreshToken;
    }

    public String createToken(String userId, Long tokenInvalidTime){
      return Jwts.builder().setSubject(userId).setIssuedAt(new Date())
              .setExpiration(new Date((new Date()).getTime() + tokenInvalidTime))
              .signWith(SignatureAlgorithm.HS512, secretKey).compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = detailService.loadUserByUsername(this.getUserNameFromJwtToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public boolean validateJwtToken(String authToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean checkRefreshToken(String refreshToken) {
        // TODO Auto-generated method stub
        String redisRT = redisUtil.getValues(refreshToken);
        return redisRT == null ? false : true;
    }

    public String getToken(String key){
        return redisUtil.getValues(key);
    }

    public void deleteRefreshToken(String refreshToken) {
        redisUtil.deleteValues(refreshToken);
    }


}
