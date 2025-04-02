package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utn.tup.ps.Entity.ResultEntity;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
}
