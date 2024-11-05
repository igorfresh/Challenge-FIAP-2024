package com.challenge.cmg.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain config(HttpSecurity http, AuthorizationFilter authFilter) throws Exception {
        http.csrf(csrf -> csrf.disable());

        // Autorização de requisições
        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/client").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/login").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/client/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/client/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/product").permitAll()
                        .requestMatchers(HttpMethod.GET, "/product/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/product/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/product/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/productCategory").permitAll()
                        .requestMatchers(HttpMethod.GET, "/productCategory/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/productCategory/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/productCategory/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/buy").permitAll()
                        .requestMatchers(HttpMethod.GET, "/buy/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/buy/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/buy/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/purchasedItens").permitAll()
                        .requestMatchers(HttpMethod.GET, "/purchasedItens/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/purchasedItens/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/purchasedItens/**").permitAll()
                        .requestMatchers("/chat/**").permitAll()
                        .anyRequest().authenticated()
        );

        // Desabilitar a proteção de frame options para o H2 console
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        // Filtro de autenticação
        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
