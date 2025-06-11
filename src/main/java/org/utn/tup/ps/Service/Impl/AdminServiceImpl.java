package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.utn.tup.ps.Dto.Login.SendEmailDto;
import org.utn.tup.ps.Dto.Teacher.AllowResponseDto;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Entity.UserEntity;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Repository.UserRepository;
import org.utn.tup.ps.Service.AdminService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final TeacherRepository teacherRepository;
    private final RestTemplate restTemplate;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Value("${comms.service.url:http://news-service:8080/api/comms}")
    private String commsServiceUrl;

    @Override
    public List<TeacherEntity> getPendingTeachers(Boolean approved) {
        return teacherRepository.findByApproved(approved);
    }

    @Override
    public TeacherEntity approveTeacher(Long teacherId) {

        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        teacher.setApproved(true);

        UserEntity user = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        SendEmailDto dto = new SendEmailDto(
                user.getEmail(),
                "Cuenta confirmada con éxito!",
                "Su cuenta ha sido habilitada para su uso en la página de El Galponcito. Gracias por su colaboración."
        );

        String endpoint = commsServiceUrl + "/send-email";

//        restTemplate.postForEntity(endpoint, dto, String.class);


        return teacherRepository.save(teacher);
    }

    @Override
    public void rejectTeacher(Long teacherId) {

        UserEntity user = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        SendEmailDto dto = new SendEmailDto(
                user.getEmail(),
                "Su cuenta ha sido denegada",
                "La cuenta que intentó registrar para El Galponcito ha sido denegada por un administrador. Si esto fue un error, comuníquese con un encargado e intente crear su cuenta nuevamente."
        );

        String endpoint = commsServiceUrl + "/send-email";

        restTemplate.postForEntity(endpoint, dto, String.class);

        teacherRepository.deleteById(teacherId);

    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void respondTeachers(List<AllowResponseDto> teacherList) {
        for (AllowResponseDto teacher : teacherList) {
            if (teacher.getApproved())
                approveTeacher(teacher.getId());
            else
                rejectTeacher(teacher.getId());
        }
    }
}