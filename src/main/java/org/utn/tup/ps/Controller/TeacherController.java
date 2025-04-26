package org.utn.tup.ps.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Entity.AuditEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class TeacherController {

    private final TeacherService service;

    @PostMapping("/createForAdmin")
    public ResponseEntity<TeacherAdminDto> createTeacherForAdmin(@RequestBody TeacherAdminDto dto){
        return new ResponseEntity<>(service.createTeacherForAdminRole(dto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherEntity>> getTeachers(){
        return new ResponseEntity<>(service.getTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherEntity> getTeacherById(@PathVariable Long id){
        return new ResponseEntity<>(service.getTeacherById(id), HttpStatus.OK);

    }


}
