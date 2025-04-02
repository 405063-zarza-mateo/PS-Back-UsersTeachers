package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.utn.tup.ps.Entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
