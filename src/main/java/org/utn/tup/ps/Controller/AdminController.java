package org.utn.tup.ps.Controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.utn.tup.ps.Entity.AuditEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Service.AdminService;
import org.utn.tup.ps.Service.AuditService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")

public class AdminController {

    private final AdminService adminService;
    private final AuditService auditService;

    @GetMapping("/pending-teachers")
    public ResponseEntity<List<TeacherEntity>> getPendingTeachers(@RequestParam Boolean approved) {
        List<TeacherEntity> pendingTeachers = adminService.getPendingTeachers(approved);
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

    @GetMapping("/logs")
    public ResponseEntity<List<AuditEntity>> getAuditLog(){
        return new ResponseEntity<>(auditService.getAuditLog(), HttpStatus.OK);
    }
}