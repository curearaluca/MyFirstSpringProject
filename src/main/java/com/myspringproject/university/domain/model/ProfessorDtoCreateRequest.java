package com.myspringproject.university.domain.model;

import lombok.*;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDtoCreateRequest {

    private Integer id;

    @ToString.Exclude
    @NotBlank(message = "CNP must not be null")
    @DecimalMin("1000000000000")
    @DecimalMax("2999999999999")
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
