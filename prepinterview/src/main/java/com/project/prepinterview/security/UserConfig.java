package com.project.prepinterview.security;

import jakarta.servlet.FilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class UserConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/registercandidate").permitAll()
                .requestMatchers("/users/registeradmin").permitAll()
                .requestMatchers("user/generateotp").permitAll()
                .requestMatchers("/user/candidate-login").permitAll()
                .requestMatchers("/user/admin-login").permitAll()
                .anyRequest().authenticated())

        .formLogin(Customizer.withDefaults())

        .build();
    }
}
