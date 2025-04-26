package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.utn.tup.ps.Dto.Student.StudentPostDto;
import org.utn.tup.ps.Entity.ResultEntity;
import org.utn.tup.ps.Entity.ReviewEntity;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Dto.Student.ReviewDto;
import org.utn.tup.ps.Repository.StudentRepository;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Repository.UserRepository;
import org.utn.tup.ps.Service.AuditService;
import org.utn.tup.ps.Service.StudentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuditService log;

    @Override
    public StudentPostDto addStudent(StudentPostDto dto, String teacherEmail) {
        StudentEntity entity = modelMapper.map(dto, StudentEntity.class);
        entity.setAssistance(0);
        studentRepository.save(entity);

        if (!teacherEmail.equals("elgalponcitoateneo@gmail.com")) {
            TeacherEntity teacher = teacherRepository.findByUser(userRepository.findByEmail(teacherEmail).get()).get();
            log.logCreate(dto.getName() + " " + dto.getLastName(), teacher.getName() + " " + teacher.getLastName());
        }

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
    public StudentEntity updateStudent(StudentEntity entity, String teacherEmail) {
        if (!teacherEmail.equals("elgalponcitoateneo@gmail.com")) {
            TeacherEntity teacher = teacherRepository.findByUser(userRepository.findByEmail(teacherEmail).get()).get();
            log.logUpdate(entity.getName() + " " + entity.getLastName(), teacher.getName() + " " + teacher.getLastName());
        }

        return studentRepository.save(entity);
    }

    @Override
    public void deleteStudent(Long id) {
        if (studentRepository.findById(id).isPresent()) {
            StudentEntity entity = studentRepository.findById(id).get();
            log.logDelete(entity.getName() + " " + entity.getLastName());
            studentRepository.deleteById(id);
        } else
            throw new RuntimeException("Student not found");
    }

    @Override
    public ReviewDto addReview(Long studentId, String teacherEmail, ReviewDto reviewDto) {
        StudentEntity studentEntity = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        TeacherEntity teacher = teacherRepository.findByUser(userRepository.findByEmail(teacherEmail).get()).orElseThrow(() -> new RuntimeException("Teacher not found."));

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setResults(new ArrayList<>());

        List<ReviewEntity> reviews = studentEntity.getReviews();
        if (!reviews.isEmpty() && Objects.equals(
                reviews.get(reviews.size() - 1).getDate(), LocalDate.now())) {
            throw new RuntimeException("El estudiante ya fue reseñado hoy");
        }

        for (int i = 0; i < reviewDto.getResultDtos().size(); i++) {
            ResultEntity resultEntity = new ResultEntity();
            resultEntity.setSubject(reviewDto.getResultDtos().get(i).getSubject());

            if (reviewDto.getResultDtos().get(i).getWorkedOn()) {
            resultEntity.setScore(reviewDto.getResultDtos().get(i).getScore());
            }

            resultEntity.setWorkedOn(reviewDto.getResultDtos().get(i).getWorkedOn());

            reviewEntity.getResults().add(resultEntity);
        }


        studentEntity.setAssistance(studentEntity.getAssistance() + 1);
        teacher.setAssistance(teacher.getAssistance() + 1);

        reviewEntity.setDate(reviewDto.getDate());
        reviewEntity.setTeacher(teacher);
        studentEntity.getReviews().add(reviewEntity);
        studentRepository.save(studentEntity);
        teacherRepository.save(teacher);

        if (!teacherEmail.equals("elgalponcitoateneo@gmail.com")) {
            log.logReview(studentEntity.getName() + " " + studentEntity.getLastName(), teacher.getName() + " " + teacher.getLastName());
        }

        return reviewDto;
    }

    @Override
    public List<Course> getCourses() {
        return List.of(Course.values());
    }

    @Scheduled(cron = "@yearly")
    @Override
    public void passCourseAfterYear() {
        List<StudentEntity> students = studentRepository.findAll();
        Course[] courses = Course.values();

        for (StudentEntity student : students) {
            Course currentCourse = student.getCourse();
            int currentIndex = currentCourse.ordinal();

            if (currentIndex < courses.length - 1) {
                student.setCourse(courses[currentIndex + 1]);
            }
        }

        studentRepository.saveAll(students);
    }


}
