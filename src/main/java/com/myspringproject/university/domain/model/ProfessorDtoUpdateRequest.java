package com.myspringproject.university.domain.model;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoUpdateRequest {

    @NotNull
    private Integer id;

    private Long cnp;

    @NotNull(message = "First name must not be null")
    @NotBlank
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank
    private String lastName;

    @ToString.Exclude
    @Min(1)
    @Max(4)
    private Integer collegeId;

}
