package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utn.tup.ps.Entity.ReviewEntity;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Models.Student;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findAllByTeacher(TeacherEntity teacher);

}
