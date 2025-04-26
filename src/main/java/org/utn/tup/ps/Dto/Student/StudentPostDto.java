package org.utn.tup.ps.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;
import org.utn.tup.ps.Enum.Gender;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentPostDto {
    String name;
    String lastName;
    String address;
    Gender gender;
    Course course;
    String familyInfo;
}
