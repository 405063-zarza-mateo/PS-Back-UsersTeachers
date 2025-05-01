package org.utn.tup.ps.Dto.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Dto.Student.ResultDto;
import org.utn.tup.ps.Enum.Course;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherReviewDto {
    private Long reviewId;
    private List<ResultDto> results;
    private LocalDate date;
    private String studentName;
    private String studentLastName;
    private Course course;
}
