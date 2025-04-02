package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.utn.tup.ps.Dto.Student.StudentPostDto;
import org.utn.tup.ps.Entity.ResultEntity;
import org.utn.tup.ps.Entity.ReviewEntity;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Models.Review;
import org.utn.tup.ps.Repository.ResultRepository;
import org.utn.tup.ps.Repository.ReviewRepository;
import org.utn.tup.ps.Repository.StudentRepository;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ReviewRepository reviewRepository;
    private final ResultRepository resultRepository;

    @Override
    public StudentPostDto addStudent(StudentPostDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setAddress(dto.getAddress());
        entity.setName(dto.getName());
        entity.setLastName(dto.getLastname());
        entity.setCourse(dto.getCourse());
        entity.setAssistance(0);
        entity.setReviews(new ArrayList<>());
        studentRepository.save(entity);
        return dto;
    }

    @Override
    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @Override
    public List<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentEntity updateStudent(StudentEntity entity) {
        return studentRepository.save(entity);
    }

    @Override
    public void deleteStudent(Long id) {
        if (studentRepository.findById(id).isPresent())
            studentRepository.deleteById(id);
        else
            throw new RuntimeException("Student not found");
      }

    @Override
    public Review addReview(Long studentId, Long teacherId, Review review) {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        TeacherEntity teacherEntity = teacherRepository.findById(teacherId).orElseThrow(() -> new RuntimeException("Teacher not found"));
        ReviewEntity reviewEntity = new ReviewEntity();

        for (int i = 0; i < review.getResults().size(); i++) {
            ResultEntity resultEntity = new ResultEntity();
            resultEntity.setScore(review.getResults().get(i).getScore());
            resultEntity.setSubject(review.getResults().get(i).getSubject());
            resultEntity.setWorkedOn(review.getResults().get(i).getWorkedOn());

            reviewEntity.getResults().add(resultEntity);
        }


        studentEntity.setAssistance(studentEntity.getAssistance() + 1);
        teacherEntity.setAssistance(teacherEntity.getAssistance() + 1);

        reviewEntity.setDate(review.getDate());
        reviewEntity.setTeacher(teacherEntity);
        studentEntity.getReviews().add(reviewEntity);
        studentRepository.save(studentEntity);
        reviewRepository.save(reviewEntity);

        return review;
    }


}
