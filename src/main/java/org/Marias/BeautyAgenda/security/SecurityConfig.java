package org.Marias.BeautyAgenda.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                        // Gestión de usuarios: solo ADMIN
                        .requestMatchers("/Usuarios/**").hasRole("ADMIN")

                        // Servicios: todos pueden ver, solo ADMIN y JEFA modifican
                        .requestMatchers(HttpMethod.GET, "/Servicios/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/Servicios/**").hasAnyRole("ADMIN", "JEFA")
                        .requestMatchers(HttpMethod.PUT, "/Servicios/**").hasAnyRole("ADMIN", "JEFA")
                        .requestMatchers(HttpMethod.DELETE, "/Servicios/**").hasAnyRole("ADMIN", "JEFA")

                        // Clientas: cualquier usuario autenticado (ADMIN, JEFA, EMPLEADA)
                        .requestMatchers(HttpMethod.DELETE, "/Clientas/**").hasAnyRole("ADMIN", "JEFA")
                        .requestMatchers("/Clientas/**").authenticated()

                        .anyRequest().authenticated()
                )

                // Autenticación HTTP Basic
                .httpBasic(httpBasic -> {});

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


