package com.nfc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection (for APIs)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow all requests (for testing)
            )
            .formLogin(formLogin -> formLogin.disable()) // Disable Form Login
            .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic Authentication

        return http.build(); // Ensure this is called only once
    }
}