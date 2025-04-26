package org.utn.tup.ps.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Dto.Login.JwtResponseDto;
import org.utn.tup.ps.Dto.Login.LoginDto;
import org.utn.tup.ps.Dto.Login.SignupResponseDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Service.Login.AuthService;

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
}