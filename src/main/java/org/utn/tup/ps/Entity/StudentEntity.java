package org.utn.tup.ps.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastName;
    @Column(nullable = true)
    private String address;
    @Enumerated(EnumType.STRING)
    private Course course;
    @OneToMany
    private List<ReviewEntity> reviews;
    //TODO: BUSCA COMO PINGO ES ESTO
    private Integer assistance;

}
