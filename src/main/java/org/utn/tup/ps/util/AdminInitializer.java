package org.utn.tup.ps.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Entity.UserEntity;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Repository.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail("elgalponcitoateneo@gmail.com")) {
            UserEntity admin = new UserEntity();
     //       admin.setId(1L); // Para asegurar que sea el ID 1
            admin.setEmail("elgalponcitoateneo@gmail.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");

            TeacherEntity teacher = new TeacherEntity();
            teacher.setApproved(true);
            teacher.setUser(admin);
            teacher.setCourse(Course.Universidad);
            teacher.setName("admin");
            teacher.setLastName(" ");

            teacherRepository.save(teacher);

            System.out.println("Usuario administrador creado con éxito!");
        }
    }
}
