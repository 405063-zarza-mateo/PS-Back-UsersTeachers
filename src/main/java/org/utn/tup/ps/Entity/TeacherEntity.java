package org.utn.tup.ps.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String lastName;

    @Enumerated(EnumType.STRING)

    private Course course;

    private Integer assistance = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    private boolean approved = false;
}
