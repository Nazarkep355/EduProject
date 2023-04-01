package com.example.eduproject.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/*","/groups/*")
                .hasAnyAuthority("USER","ADMIN")
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/admin/**")
                .hasAuthority("ADMIN")
                .and()
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .successForwardUrl("/log")
                .and()
                .logout()
                .logoutSuccessUrl("/")
        ;


        return http.build();
    }


}
