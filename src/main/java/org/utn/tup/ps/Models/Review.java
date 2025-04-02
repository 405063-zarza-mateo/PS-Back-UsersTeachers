package org.utn.tup.ps.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Review {
    List<Result> results;
    Teacher teacher;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;
}
