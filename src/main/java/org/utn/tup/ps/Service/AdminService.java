package org.utn.tup.ps.Service;

import org.utn.tup.ps.Entity.TeacherEntity;

import java.util.List;

public interface AdminService {

    List<TeacherEntity> getPendingTeachers(Boolean approved);

    TeacherEntity approveTeacher(Long teacherId);

    void rejectTeacher(Long teacherId);
}
