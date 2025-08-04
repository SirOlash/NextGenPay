package com.NextGenPay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF with the new lambda style
                .csrf(csrf -> csrf.disable())

                // Switch to the new authorization DSL
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**",
                                "/api/v1/seller-admin/view-adminProfile")
                        .authenticated()
                        .anyRequest().permitAll()
                )

                // OAuth2 Resource Server remains the same
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt()
                );

        return http.build();
    }
}

