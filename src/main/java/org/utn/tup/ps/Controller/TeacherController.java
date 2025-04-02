package org.utn.tup.ps.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping("/createForAdmin")
    public ResponseEntity<TeacherAdminDto> createTeacherForAdmin(TeacherAdminDto dto){
        return new ResponseEntity<>(service.createTeacherForAdminRole(dto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TeacherEntity>> getTeachers(){
        //TODO: AGREGAR CAPACIDAD DE FILTRADO
        return new ResponseEntity<>(service.getTeachers(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TeacherEntity> getTeacherById(Long id){
        return new ResponseEntity<>(service.getTeacherById(id), HttpStatus.OK);

    }
}
