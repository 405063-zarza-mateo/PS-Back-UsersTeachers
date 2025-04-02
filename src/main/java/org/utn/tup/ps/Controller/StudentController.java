package org.utn.tup.ps.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Dto.Student.StudentPostDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Models.Student;
import org.utn.tup.ps.Service.StudentService;
import org.utn.tup.ps.Service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping("/create")
    public ResponseEntity<StudentPostDto> createTeacher(StudentPostDto postDto){
        return new ResponseEntity<>(service.addStudent(postDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentEntity>> getStudents(){
        //TODO: AGREGAR CAPACIDAD DE FILTRADO
        return new ResponseEntity<>(service.getStudents(), HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<StudentEntity> getStudentById(Long id){
        return new ResponseEntity<>(service.getStudentById(id), HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<StudentEntity> updateStudent(StudentEntity entity){
        return new ResponseEntity<>(service.updateStudent(entity), HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteStudent(Long id){
        service.deleteStudent(id);
        return new ResponseEntity<>("Deleted student.", HttpStatus.OK);
    }
}
