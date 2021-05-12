package com.myspringproject.university.domain.model;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoUpdateRequest {

    private Integer id;

    @NotNull(message = "First name must not be null")
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    @Email
    private String mail;

    @Min(value = 1400, message = "Salary must not be less than the minimum average salary")
    private Integer salary;

}
