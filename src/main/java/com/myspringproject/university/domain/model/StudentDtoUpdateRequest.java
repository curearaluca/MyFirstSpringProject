package com.myspringproject.university.domain.model;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoUpdateRequest {

    private Integer id;

    private Long cnp;

    @NotNull(message = "First name must not be null")
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Email
    private String mail;

    @Min(value=0, message = "Grade must not be less than 0")
    @Max(value = 10, message = "Grade must not be greater than 10")
    private Double finalGrade;

    @Min(value = 1, message = "College must not be less than 1")
    @Max(value = 6, message = "College must not be greater than 6")
    private Integer collegeId;

}
