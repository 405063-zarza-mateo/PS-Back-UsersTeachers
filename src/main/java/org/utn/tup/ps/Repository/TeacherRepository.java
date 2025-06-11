package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.utn.tup.ps.Entity.TeacherEntity;
import org.utn.tup.ps.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    List<TeacherEntity> findByApproved(boolean approved);
    Optional<TeacherEntity> findByUserId(Long id);
    Optional<TeacherEntity> findByUser(UserEntity user);
    List<TeacherEntity> findByActiveTrue();

    @Query("""
    SELECT t.course, r.date, COUNT(DISTINCT t.id)
    FROM TeacherEntity t
    JOIN ReviewEntity r ON r.teacher = t
    WHERE t.active = true
    GROUP BY t.course, r.date
    ORDER BY r.date, t.course
    """)
    List<Object[]> getTeacherAssistancesByCourseAndDate();
}
