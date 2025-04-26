package org.utn.tup.ps.Dto.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Subject;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ResultDto {
    private Subject subject;
    private Double score;
    private Boolean workedOn;

}
