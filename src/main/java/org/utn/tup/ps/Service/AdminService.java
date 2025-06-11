package org.utn.tup.ps.Service;

import org.utn.tup.ps.Dto.Teacher.AllowResponseDto;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Entity.UserEntity;

import java.util.List;

public interface AdminService {

    List<TeacherEntity> getPendingTeachers(Boolean approved);

    TeacherEntity approveTeacher(Long teacherId);

    void rejectTeacher(Long teacherId);

    List<UserEntity> getUsers();

    void respondTeachers(List<AllowResponseDto> teacherList);

}
