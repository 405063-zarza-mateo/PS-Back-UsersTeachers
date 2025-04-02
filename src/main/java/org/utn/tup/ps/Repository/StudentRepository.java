package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utn.tup.ps.Entity.StudentEntity;
@Repository

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
