package org.utn.tup.ps.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Enum.Gender;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private Gender gender;
    @Column(nullable = true)
    private String address;
    @Enumerated(EnumType.STRING)
    private Course course;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;
    //TODO: BUSCA COMO PINGO ES ESTO
    private Integer assistance;
    @Column(nullable = true)
    private String familyInfo;

}
