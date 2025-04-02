package org.utn.tup.ps.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Service.AdminService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/pending-teachers")
    public ResponseEntity<List<TeacherEntity>> getPendingTeachers() {
        List<TeacherEntity> pendingTeachers = adminService.getPendingTeachers();
        return new ResponseEntity<>(pendingTeachers, HttpStatus.OK);
    }

    @PutMapping("/approve-teacher/{id}")
    public ResponseEntity<TeacherEntity> approveTeacher(@PathVariable Long id) {
        TeacherEntity approvedTeacher = adminService.approveTeacher(id);
        return new ResponseEntity<>(approvedTeacher, HttpStatus.OK);
    }

    @DeleteMapping("/reject-teacher/{id}")
    public ResponseEntity<?> rejectTeacher(@PathVariable Long id) {
        adminService.rejectTeacher(id);
        return new ResponseEntity<>("Profesor rechazado", HttpStatus.OK);
    }
}