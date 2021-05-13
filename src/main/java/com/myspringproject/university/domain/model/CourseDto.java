package com.myspringproject.university.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Integer id;
    private String name;
    private Integer credits;
    private ProfessorDto professorDto;
    private CollegeDto collegeDto;

}
