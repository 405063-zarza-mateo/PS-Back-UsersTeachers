package org.utn.tup.ps.Service;

import org.utn.tup.ps.Dto.Student.ReviewDto;
import org.utn.tup.ps.Dto.Student.StudentPostDto;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Models.Review;

import java.util.List;

public interface StudentService {

    StudentPostDto addStudent(StudentPostDto dto);

    StudentEntity getStudentById(Long id);
    List<StudentEntity> getStudents();

    StudentEntity updateStudent(StudentEntity entity);

    void deleteStudent(Long id);

    Review addReview(Long studentId, Long teacherId, Review review);
}
