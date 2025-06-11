package org.utn.tup.ps.Service;

import org.utn.tup.ps.Dto.Login.ProfileDto;
import org.utn.tup.ps.Dto.Student.CourseAssistanceDto;
import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Dto.Teacher.TeacherReviewDto;
import org.utn.tup.ps.Entity.AuditEntity;
import org.utn.tup.ps.Entity.ReviewEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Enum.Course;

import java.util.List;

public interface TeacherService {
    TeacherAdminDto createTeacherForAdminRole(TeacherAdminDto dto);

    List<TeacherEntity> getTeachers();

    TeacherEntity getTeacherById(Long id);

    TeacherEntity updateTeacher(TeacherEntity entity);

    void deleteTeacher(Long id);

    List<TeacherReviewDto> getReviewsByTeacher(Long id);

    void passCourseAfterYear();

    ProfileDto getProfileByEmail(String email);

    ProfileDto updateCourse(String email, Course course);

    List<CourseAssistanceDto> getTeacherAssistances();
}
