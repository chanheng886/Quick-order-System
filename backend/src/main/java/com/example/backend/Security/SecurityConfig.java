package com.example.backend.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration 
@EnableWebSecurity 
@RequiredArgsConstructor 
public class SecurityConfig {
    private final JwtAuthenticationFilter authenticationFilter;
    private static final String[] WHITE_LIST_URL ={
        "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**"
    };
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth->auth
                .requestMatchers(WHITE_LIST_URL).permitAll()
                // for users
                .requestMatchers(HttpMethod.GET, "/api/v1/product/**").permitAll()
                // for admin
                .requestMatchers(HttpMethod.POST, "/api/v1/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/product/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/product/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
    }
}