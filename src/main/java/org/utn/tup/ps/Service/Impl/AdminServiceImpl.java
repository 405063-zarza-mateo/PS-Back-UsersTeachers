package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Service.AdminService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final TeacherRepository teacherRepository;

    // Obtener todos los profesores pendientes de aprobación
    public List<TeacherEntity> getPendingTeachers() {
        return teacherRepository.findByApproved(false);
    }

    // Aprobar un profesor
    public TeacherEntity approveTeacher(Long teacherId) {
        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        teacher.setApproved(true);
        return teacherRepository.save(teacher);
    }

    // Rechazar un profesor
    public void rejectTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
