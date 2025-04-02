package org.utn.tup.ps.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String name;
    Integer quantity;
}
