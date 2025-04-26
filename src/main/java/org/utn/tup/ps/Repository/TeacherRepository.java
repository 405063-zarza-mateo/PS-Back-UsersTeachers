package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
