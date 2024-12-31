package com.app.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/journal/**").permitAll()  // Permit access to `/journal/**`
                .anyRequest().authenticated()  // Secure all other endpoints
            )
            .httpBasic(); // Enable basic authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Creating an in-memory user for development
        return username -> User
            .withUsername("user")  // Username
            .password(passwordEncoder().encode("password"))  // Password
            .roles("USER")  // User role
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Password encoder (BCrypt recommended)
    }
}
