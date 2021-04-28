package com.myspringproject.university.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeanDto {

    private BigInteger cnp;
    private String firstName;
    private String lastName;
    private LocalDate dateOfInstalment;
    private CollegeDto collegeDto;

}
