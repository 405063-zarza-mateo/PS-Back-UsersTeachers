package org.utn.tup.ps.Service.Login;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.utn.tup.ps.Dto.Login.JwtResponseDto;
import org.utn.tup.ps.Dto.Login.LoginDto;
import org.utn.tup.ps.Dto.Login.SendEmailDto;
import org.utn.tup.ps.Dto.Login.SignupResponseDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Entity.UserEntity;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Repository.UserRepository;
import org.utn.tup.ps.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final RestTemplate restTemplate;

    @Value("${comms.service.url:http://news-service:8080/api/comms}")
    private String commsServiceUrl;

    public SignupResponseDto registerTeacher(TeacherPostDto teacherDto) {
        if (userRepository.existsByEmail(teacherDto.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }

        UserEntity user = new UserEntity();
        user.setEmail(teacherDto.getEmail());
        user.setPassword(passwordEncoder.encode(teacherDto.getPassword()));
        user.setRole("ROLE_TEACHER");

        TeacherEntity teacher = new TeacherEntity();
        teacher.setName(teacherDto.getName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setCourse(teacherDto.getCourse());
        teacher.setAssistance(0);
        teacher.setApproved(false);
        teacher.setUser(user);

        teacherRepository.save(teacher);


        return new SignupResponseDto("Registro exitoso. Pendiente de aprobación por el administrador.");
    }

    public JwtResponseDto authenticateUser(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();


        return new JwtResponseDto(
                jwt,
                "Bearer",
                userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority()
        );
    }

    public boolean changePassword(String email, String currentPassword, String newPassword) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return true;
    }

    public String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        java.util.Random random = new java.util.Random();

        // Generamos una contraseña de 10 caracteres
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }

        return sb.toString();
    }

    public boolean resetPassword(String email) {
        try {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            String newPassword = generateRandomPassword();

            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            SendEmailDto dto = new SendEmailDto(
                    user.getEmail(),
                    "Recuperación de contraseña - El Galponcito",
                    "Se ha solicitado la recuperación de su contraseña. Su nueva contraseña es: " + newPassword +
                            "\n\nPor favor, cambie esta contraseña después de iniciar sesión por motivos de seguridad."
            );

            String endpoint = commsServiceUrl + "/send-email";
            restTemplate.postForEntity(endpoint, dto, String.class);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}