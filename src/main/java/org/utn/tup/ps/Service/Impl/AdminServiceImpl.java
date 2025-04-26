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

    public List<TeacherEntity> getPendingTeachers(Boolean approved) {
        return teacherRepository.findByApproved(approved);
    }

    public TeacherEntity approveTeacher(Long teacherId) {
        TeacherEntity teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        teacher.setApproved(true);
        return teacherRepository.save(teacher);
    }

    public void rejectTeacher(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }
}
