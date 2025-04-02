package org.utn.tup.ps.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String name;
    private String lastName;
    private User user;
    private Course course;
    private Integer assistance;

//TODO: ANADIR ASISTENCIAS
}
