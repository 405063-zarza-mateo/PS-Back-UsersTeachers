package org.utn.tup.ps.Service;

import org.utn.tup.ps.Dto.Student.StudentPostDto;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Dto.Student.ReviewDto;

import java.util.List;

public interface StudentService {

    StudentPostDto addStudent(StudentPostDto dto, String teacherEmail);

    StudentEntity getStudentById(Long id);

    List<StudentEntity> getStudents();

    StudentEntity updateStudent(StudentEntity entity, String teacherEmail);

    void deleteStudent(Long id);

    ReviewDto addReview(Long studentId, String teacherEmail, ReviewDto reviewDto);

    List<Course> getCourses();

    void passCourseAfterYear();
}
