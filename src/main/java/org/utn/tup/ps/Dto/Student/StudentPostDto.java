package org.utn.tup.ps.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPostDto {
    String name;
    String lastname;
    String address;
    Course course;
}
