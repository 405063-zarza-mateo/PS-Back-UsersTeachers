package org.utn.tup.ps.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Dto.Login.JwtResponseDto;
import org.utn.tup.ps.Dto.Login.LoginDto;
import org.utn.tup.ps.Dto.Login.PasswordChangeDto;
import org.utn.tup.ps.Dto.Login.SignupResponseDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Service.Login.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> registerTeacher(@RequestBody TeacherPostDto teacherDto) {
        SignupResponseDto response = authService.registerTeacher(teacherDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            JwtResponseDto jwtResponse = authService.authenticateUser(loginDto);
            return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred during authentication: " + e.getMessage());
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody PasswordChangeDto passwordChangeDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();

        boolean success = authService.changePassword(
                currentUserEmail,
                passwordChangeDto.getCurrentPassword(),
                passwordChangeDto.getNewPassword()
        );

        if (success) {
            return new ResponseEntity<>(Map.of("message", "Contraseña actualizada correctamente"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "No se pudo actualizar la contraseña"), HttpStatus.BAD_REQUEST);
        }
    }
}