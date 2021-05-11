package com.myspringproject.university.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeanDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private Long deanCnp;
    private LocalDate dateOfInstalment;
    private String title;

}
