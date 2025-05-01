package org.utn.tup.ps.Dto.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private String email;
    private String name;
    private String lastName;
    private Course course;
}
