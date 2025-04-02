package org.utn.tup.ps.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.utn.tup.ps.Entity.UserEntity;
import org.utn.tup.ps.Repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Crear usuario admin si no existe
        if (!userRepository.existsByEmail("elgalponcitoateneo@gmail.com")) {
            UserEntity admin = new UserEntity();
            admin.setId(1L); // Para asegurar que sea el ID 1
            admin.setEmail("admin@ejemplo.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
            System.out.println("Usuario administrador creado con éxito!");
        }
    }
}
