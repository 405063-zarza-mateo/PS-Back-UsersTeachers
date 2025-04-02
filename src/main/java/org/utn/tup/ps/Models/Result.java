package org.utn.tup.ps.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Subject;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Result {
    private Subject subject;
    private Double score;
    private Boolean workedOn;

}
