package org.utn.tup.ps.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Subject;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Subject subject;

    private Double score;

    private Boolean workedOn;
}
