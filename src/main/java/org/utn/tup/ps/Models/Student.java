package org.utn.tup.ps.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.utn.tup.ps.Enum.Course;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Student {
    private String name;
    private String lastName;
    private String address;
    private Course course;
    private List<Review> results;
}
