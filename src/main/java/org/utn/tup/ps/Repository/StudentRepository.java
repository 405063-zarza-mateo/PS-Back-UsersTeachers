package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utn.tup.ps.Dto.Teacher.TeacherReviewDto;
import org.utn.tup.ps.Entity.StudentEntity;
import org.utn.tup.ps.Entity.TeacherEntity;

import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("""
            SELECT new org.utn.tup.ps.Dto.Teacher.TeacherReviewDto(
                  r.id,
                  null,
                  r.date,
                  s.name,
                  s.lastName,
                  s.course
              )
              FROM StudentEntity s
              JOIN s.reviews r
              WHERE r.teacher.id = :teacherId""")
    List<TeacherReviewDto> findReviewsWithStudentByTeacherId(@Param("teacherId") Long teacherId);

    List<StudentEntity> findByActiveTrue();

    @Query("""
    SELECT s.course, r.date, COUNT(s.id)
    FROM StudentEntity s
    JOIN s.reviews r
    WHERE s.active = true
    GROUP BY s.course, r.date
    ORDER BY r.date, s.course
    """)
    List<Object[]> getStudentAssistancesByCourseAndDate();
}

