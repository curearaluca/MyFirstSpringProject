package com.myspringproject.university.domain.model;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeanDtoUpdateRequest {

    private Integer id;

    @NotNull(message = "Cnp must not be null")
    @NotBlank
    @Digits(integer = 13, fraction = 0)
    private Long cnp;

    @NotNull(message = "First name must not be null")
    @NotBlank(message = "First name must not be blank")
    private String firstName;

    @NotNull(message = "Last name must not be null")
    @NotBlank(message = "Last name must not be blank")
    private String lastName;

    private LocalDate dateOfInstalment;

    private String title;

    public BigInteger getId(){
        return BigInteger.valueOf(this.id);
    }
}
