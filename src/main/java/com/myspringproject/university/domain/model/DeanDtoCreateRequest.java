package com.myspringproject.university.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeanDtoCreateRequest {

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

    @Builder.Default
    private LocalDate dateOfInstalment=LocalDate.now();

    private String title;

}
