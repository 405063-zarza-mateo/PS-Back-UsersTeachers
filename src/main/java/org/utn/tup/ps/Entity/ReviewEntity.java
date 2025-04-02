package org.utn.tup.ps.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Models.Teacher;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<ResultEntity> results;
    @ManyToOne
    private TeacherEntity teacher;
    //TODO: REVISAR EN CASO DE ERROR
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC-3")
    private LocalDate date;
}
