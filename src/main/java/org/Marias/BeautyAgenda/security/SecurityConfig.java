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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // Deshabilitamos CSRF porque estamos haciendo una REST API
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())

                // La API no necesita sesiones
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // Configuración de las rutas
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/internal/**").permitAll()
                        // Gestión de usuarios: solo ADMIN
                        .requestMatchers("/Usuarios/me").authenticated()
                        .requestMatchers("/Usuarios/**").hasRole("ADMIN")
                        .requestMatchers("/Empleadas/**").hasRole("ADMIN")
                        // Plantillas: todos pueden ver (las necesitan para asignar servicios), solo ADMIN modifica
                        .requestMatchers(HttpMethod.GET, "/Plantillas/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/Plantillas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Plantillas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Plantillas/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/Mensajes/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/Mensajes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Mensajes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Mensajes/**").hasRole("ADMIN")

                        // Servicios: todos pueden ver, solo ADMIN y JEFA modifican
                        .requestMatchers(HttpMethod.GET, "/Servicios/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/Servicios/**").hasAnyRole("ADMIN", "JEFA")
                        .requestMatchers(HttpMethod.PUT, "/Servicios/**").hasAnyRole("ADMIN", "JEFA")
                        .requestMatchers(HttpMethod.DELETE, "/Servicios/**").hasAnyRole("ADMIN", "JEFA")

                        // Clientas: cualquier usuario autenticado (ADMIN, JEFA, EMPLEADA)
                        .requestMatchers(HttpMethod.DELETE, "/Clientas/**").hasAnyRole("ADMIN", "JEFA")
                        .requestMatchers("/Clientas/**").authenticated()

                        .requestMatchers("/Citas/**").authenticated()

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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(
                "https://mariasbeautysalon.org",
                "http://localhost:5173" // tu servidor de desarrollo de Vite
        ));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}



