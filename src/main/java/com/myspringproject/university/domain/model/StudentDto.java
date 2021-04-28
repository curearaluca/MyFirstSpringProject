package com.myspringproject.university.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer id;
    private BigInteger cnp;
    private String firstName;
    private String lastName;
    private CollegeDto collegeDto;

}
