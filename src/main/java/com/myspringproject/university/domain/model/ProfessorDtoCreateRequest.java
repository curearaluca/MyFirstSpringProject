package com.myspringproject.university.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoCreateRequest {

    private Integer id;

    @NotNull(message = "CNP must not be null")
    @DecimalMin("1000000000000")
    @DecimalMax("2999999999999")
    @Digits(integer = 13, fraction = 0)
    private Long cnp;

    @NotNull(message = "First name must not be null")
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @NotNull(message = "Salary must not be null")
    @NotBlank(message = "Salary must not be blank")
    @Min(value = 1400, message = "Salary must not be less than the minimum average salary")
    private Integer salary;

    @Email
    private String mail;

    @NotNull(message = "College id must not be null")
    @Min(1)
    @Max(6)
    private Integer collegeId;

}
