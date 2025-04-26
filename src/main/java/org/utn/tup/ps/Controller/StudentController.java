package org.utn.tup.ps.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Dto.Student.StudentPostDto;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Dto.Student.ReviewDto;
import org.utn.tup.ps.Service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class StudentController {
    private final StudentService service;

    @PostMapping("/create")
    public ResponseEntity<StudentPostDto> createStudent(@RequestBody StudentPostDto postDto, @RequestParam String teacherEmail) {
        return new ResponseEntity<>(service.addStudent(postDto, teacherEmail), HttpStatus.CREATED);
    }

    @PostMapping("/addReview")
    public ResponseEntity<ReviewDto> createStudent(@RequestParam Long studentId, @RequestParam String teacherEmail, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(service.addReview(studentId, teacherEmail, reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentEntity>> getStudents(){
        return new ResponseEntity<>(service.getStudents(), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getStudentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentEntity> updateStudent(@PathVariable Long id, @RequestBody StudentEntity entity, @RequestParam  String teacherEmail) {
        entity.setId(id);
        return new ResponseEntity<>(service.updateStudent(entity, teacherEmail), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return new ResponseEntity<>("Deleted student.", HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(){
        return  new ResponseEntity<>(service.getCourses(), HttpStatus.OK);
    }
}
