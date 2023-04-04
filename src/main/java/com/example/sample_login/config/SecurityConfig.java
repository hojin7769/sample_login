package com.example.sample_login.config;

import com.example.sample_login.jwt.AuthEntryPointJwt;
import com.example.sample_login.jwt.JwtFilter;
import com.example.sample_login.service.member.service.AuthDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {


    private final AuthDetailService detailService;

    private final AuthEntryPointJwt unauthorizedHandler;

    private final JwtFilter authenticationJwtTokenFilter;

    public SecurityConfig(@Autowired AuthDetailService detailService
            , @Autowired AuthEntryPointJwt unauthorizedHandler
            , @Autowired JwtFilter authenticationJwtTokenFilter) {
        this.detailService = detailService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.authenticationJwtTokenFilter = authenticationJwtTokenFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter authenticationJwtTokenFilter() {
        return authenticationJwtTokenFilter;
    }

//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//       authenticationConfiguration.authenticationManagerBuilder().
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    @Bean
    public AuthenticationManager authenticationManager(@Autowired AuthDetailService userDetailsService){
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }
//    @Bean
//    public UserDetailsService inMemoryUserDetailsManager(){
//        return new InMemoryUserDetailsManager(
//                User.withUsername("user1").password(passwordEncoder().encode("1234")).authorities("USER").build(),
//                User.withUsername("user2").password(passwordEncoder().encode("1234")).authorities("USER").build(),
//                User.withUsername("admin").password(passwordEncoder().encode("1234")).authorities("USER","ADMIN").build()
//        );
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin().disable();

        http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }








}
