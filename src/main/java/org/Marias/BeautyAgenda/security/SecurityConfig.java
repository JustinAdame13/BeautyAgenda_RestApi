package org.Marias.BeautyAgenda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Deshabilitamos CSRF porque estamos haciendo una REST API
                .csrf(csrf -> csrf.disable())

                // La API no necesita sesiones
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Configuración de las rutas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/Clientas/**").authenticated()
                        .anyRequest().authenticated()
                )

                // Autenticación HTTP Basic
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}


