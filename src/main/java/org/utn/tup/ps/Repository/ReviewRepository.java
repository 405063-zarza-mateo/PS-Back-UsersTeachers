package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utn.tup.ps.Entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
}
