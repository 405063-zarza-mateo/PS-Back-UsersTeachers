package org.utn.tup.ps.Service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.utn.tup.ps.Dto.Teacher.TeacherAdminDto;
import org.utn.tup.ps.Dto.Teacher.TeacherPostDto;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Repository.StudentRepository;
import org.utn.tup.ps.Repository.TeacherRepository;
import org.utn.tup.ps.Repository.UserRepository;
import org.utn.tup.ps.Service.TeacherService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public TeacherAdminDto createTeacherForAdminRole(TeacherAdminDto dto) {
        TeacherEntity entity = modelMapper.map(dto, TeacherEntity.class);
        entity.setUser(userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"))); // en teoria user 1 es admin siempre
        entity.setApproved(true);
        teacherRepository.save(entity);
        return  dto;
    }

    @Override
    public List<TeacherEntity> getTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public TeacherEntity getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @Override
    public TeacherEntity updateTeacher(TeacherEntity entity) {
        return teacherRepository.save(entity);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (teacherRepository.findById(id).isPresent())
            teacherRepository.deleteById(id);
        else
            throw new RuntimeException("Teacher not found");
    }
}
