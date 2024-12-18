package com.nfc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
	        .cors(cors -> cors.configurationSource(request -> {
	            var corsConfig = new org.springframework.web.cors.CorsConfiguration();
	            corsConfig.setAllowedOrigins(List.of("http://localhost:3000", "https://your-deployed-react-url.com"));
	            corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	            corsConfig.setAllowedHeaders(List.of("*"));
	            corsConfig.setAllowCredentials(true);
	            var source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
	            source.registerCorsConfiguration("/api/**", corsConfig);
	            return source.getCorsConfiguration(request);
	        }))
	        .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/api/**").permitAll()
                .anyRequest().permitAll() // Allow all requests (for testing)
            )
            .formLogin(formLogin -> formLogin.disable()) // Disable Form Login
            .httpBasic(httpBasic -> httpBasic.disable()); // Disable HTTP Basic Authentication

        return http.build();
    }
}
