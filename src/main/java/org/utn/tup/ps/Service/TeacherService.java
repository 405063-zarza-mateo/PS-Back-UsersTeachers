package org.utn.tup.ps.Service;

import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Entity.AuditEntity;
import org.utn.tup.ps.Entity.TeacherEntity;

import java.util.List;

public interface TeacherService {
    TeacherAdminDto createTeacherForAdminRole(TeacherAdminDto dto);

    List<TeacherEntity> getTeachers();

    TeacherEntity getTeacherById(Long id);

    TeacherEntity updateTeacher(TeacherEntity entity);

    void deleteTeacher(Long id);

}
