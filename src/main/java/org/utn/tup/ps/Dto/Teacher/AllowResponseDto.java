package org.utn.tup.ps.Dto.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllowResponseDto {
    Long id;
    Boolean approved;
}
