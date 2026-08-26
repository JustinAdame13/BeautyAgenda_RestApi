package org.Marias.BeautyAgenda.security;

import org.Marias.BeautyAgenda.entity.Usuario;
import org.Marias.BeautyAgenda.entity.enums.RolUsuario;
import org.Marias.BeautyAgenda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    public AdminSeeder(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        boolean existeAdmin = usuarioRepository.findByUsername(adminUsername).isPresent();

        if (!existeAdmin) {
            Usuario admin = new Usuario();
            admin.setUsername(adminUsername);
            admin.setPasswordHash(passwordEncoder.encode(adminPassword));
            admin.setRol(RolUsuario.ADMIN);
            admin.setActivo(true);

            usuarioRepository.save(admin);
            System.out.println("Usuario ADMIN inicial creado: " + adminUsername);
        }
    }
}