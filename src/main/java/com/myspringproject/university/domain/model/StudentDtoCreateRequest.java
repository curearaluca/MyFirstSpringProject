package com.myspringproject.university.domain.model;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoCreateRequest {

    private Integer id;

    @NotNull(message = "CNP must not be null")
    @DecimalMin("1000000000000")
    @DecimalMax("2999999999999")
    @Digits(integer = 13, fraction = 0)
    private Long cnp;

    @NotNull(message = "First name must not be null")
    @NotBlank
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank
    private String lastName;

    @Email
    private String mail;

    @Builder.Default
    private Double finalGrade= Double.valueOf(0);

    @Min(1)
    @Max(6)
    private Integer collegeId;

}
