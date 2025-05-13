package org.utn.tup.ps.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Dto.Login.ProfileDto;
import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Dto.Teacher.TeacherReviewDto;
import org.utn.tup.ps.Entity.AuditEntity;
import org.utn.tup.ps.Entity.ReviewEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Models.Teacher;
import org.utn.tup.ps.Service.TeacherService;

import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}")
    public ResponseEntity<TeacherEntity> updateTeacher(@PathVariable Long id, @RequestBody TeacherEntity teacherEntity){
       teacherEntity.setId(id);
        return new ResponseEntity<>(service.updateTeacher(teacherEntity), HttpStatus.OK);

    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<TeacherReviewDto>> getReviewsByTeacher(@PathVariable Long id){
        return new ResponseEntity<>(service.getReviewsByTeacher(id), HttpStatus.OK);

    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getTeacherProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        ProfileDto profile = service.getProfileByEmail(currentUserEmail);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<ProfileDto> updateTeacherProfile(@RequestBody Map<String, String> updateData) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserEmail = authentication.getName();
        Course course = Course.valueOf(updateData.get("curso"));
        ProfileDto updatedProfile = service.updateCourse(currentUserEmail, course);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        service.deleteTeacher(id);
        return new ResponseEntity<>("Deleted teacher.", HttpStatus.OK);
    }
}
