package org.utn.tup.ps.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.utn.tup.ps.Entity.InventoryItemEntity;
@Repository

public interface InventoryRepository extends JpaRepository<InventoryItemEntity, Long> {
}
