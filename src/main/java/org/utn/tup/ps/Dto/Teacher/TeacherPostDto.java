package org.utn.tup.ps.Dto.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherPostDto {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private Course course;
}
