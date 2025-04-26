package org.utn.tup.ps.Dto.Student;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Models.Teacher;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReviewDto {
    List<ResultDto> resultDtos;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
