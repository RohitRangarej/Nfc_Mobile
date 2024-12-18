package com.nfc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity sec) throws Exception {
        sec
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection (for APIs)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow all requests (for testing)
            )
            .formLogin(formLogin -> formLogin.disable()) // Disable Form Login
            .httpBasic(httpBasic -> httpBasic.disable()) // Disable HTTP Basic Authentication
            .cors(cors -> cors.configurationSource(corsConfigurationSource())); // Apply custom CORS configuration
        
        return sec.build();
    }

    /**
     * Define a custom CORS configuration source.
     * Adjust allowed origins, methods, and headers as per your requirements.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
            "http://localhost:3000",
            "https://nfc-profile.onrender.com" // Your production domain
        ));
        configuration.setAllowedMethods(List.of(
            "GET", "POST", "PUT", "DELETE", "OPTIONS" // Allowed HTTP methods
        ));
        configuration.setAllowedHeaders(List.of(
            "Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin" // Allowed headers
        ));
        configuration.setExposedHeaders(List.of(
            "Authorization", "Content-Disposition" // Headers exposed to the client
        ));
        configuration.setAllowCredentials(true); // Allow cookies or credentials
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Apply to all endpoints
        return source;
    }
}
