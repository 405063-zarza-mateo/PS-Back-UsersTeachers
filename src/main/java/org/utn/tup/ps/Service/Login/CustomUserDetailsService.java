package org.utn.tup.ps.Service.Login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Entity.UserEntity;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        // Si es un profesor, verificar si está aprobado
        if ("ROLE_TEACHER".equals(user.getRole())) {
            Optional<TeacherEntity> teacher = teacherRepository.findByUserId(user.getId());
            if (teacher.isPresent() && !teacher.get().isApproved()) {
                throw new RuntimeException("Su cuenta está pendiente de aprobación por el administrador");
            }
        }

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}