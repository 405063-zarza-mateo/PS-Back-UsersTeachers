package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.utn.tup.ps.Dto.Login.ProfileDto;
import org.utn.tup.ps.Dto.Student.ResultDto;
import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Dto.Teacher.TeacherReviewDto;
import org.utn.tup.ps.Entity.*;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Models.Teacher;
import org.utn.tup.ps.Repository.*;
import org.utn.tup.ps.Service.TeacherService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final StudentRepository studentRepository;

    @Override
    public TeacherAdminDto createTeacherForAdminRole(TeacherAdminDto dto) {
        TeacherEntity entity = modelMapper.map(dto, TeacherEntity.class);
        entity.setUser(userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"))); // en teoria user 1 es admin siempre
        entity.setApproved(true);
        teacherRepository.save(entity);
        return dto;
    }

    @Override
    public List<TeacherEntity> getTeachers() {
        return teacherRepository.findByActiveTrue();
    }

    @Override
    public TeacherEntity getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @Override
    public TeacherEntity updateTeacher(TeacherEntity entity) {
        TeacherEntity old = teacherRepository.findById(entity.getId()).get();
        if (old.getAssistance() != null)
            entity.setAssistance(old.getAssistance());
        return teacherRepository.save(entity);
    }

    @Override
    public void deleteTeacher(Long id) {
        TeacherEntity teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setActive(false);
        teacherRepository.save(teacher);
    }

    @Override
    public List<TeacherReviewDto> getReviewsByTeacher(Long id) {
        List<TeacherReviewDto> partialDtos = studentRepository.findReviewsWithStudentByTeacherId(id);

        Map<Long, ReviewEntity> reviewMap = reviewRepository.findAllById(
                partialDtos.stream().map(TeacherReviewDto::getReviewId).toList()
        ).stream().collect(Collectors.toMap(ReviewEntity::getId, r -> r));

        return partialDtos.stream().map(dto -> {
            List<ResultDto> resultDtos = reviewMap.get(dto.getReviewId()).getResults().stream()
                    .map(result -> modelMapper.map(result, ResultDto.class))
                    .toList();

            return new TeacherReviewDto(
                    dto.getReviewId(),
                    resultDtos,
                    dto.getDate(),
                    dto.getStudentName(),
                    dto.getStudentLastName(),
                    dto.getCourse()
            );
        }).toList();
    }

    @Scheduled(cron = "@yearly")
    @Override
    public void passCourseAfterYear() {
        List<TeacherEntity> teachers = teacherRepository.findByActiveTrue();
        Course[] courses = Course.values();

        for (TeacherEntity teacher : teachers) {
            Course currentCourse = teacher.getCourse();
            int currentIndex = currentCourse.ordinal();

            if (currentIndex < courses.length - 1) {
                teacher.setCourse(courses[currentIndex + 1]);
            }
        }

        teacherRepository.saveAll(teachers);
    }

    public ProfileDto getProfileByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TeacherEntity teacher = teacherRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(user.getEmail());
        profileDto.setName(teacher.getName());
        profileDto.setLastName(teacher.getLastName());
        profileDto.setCourse(teacher.getCourse());

        return profileDto;
    }


    public ProfileDto updateCourse(String email, Course course) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TeacherEntity teacher = teacherRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        teacher.setCourse(course);
        teacherRepository.save(teacher);

        ProfileDto profileDto = new ProfileDto();
        profileDto.setEmail(user.getEmail());
        profileDto.setName(teacher.getName());
        profileDto.setLastName(teacher.getLastName());
        profileDto.setCourse(teacher.getCourse());

        return profileDto;
    }
}
