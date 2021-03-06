package com.myspringproject.university.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Integer id;

    @NotBlank(message = "CNP must not be null")
    private Long cnp;
    private String firstName;
    private String lastName;
    private String mail;
    private Double finalGrade;
    private CollegeDto college;

}
