package com.myspringproject.university.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

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
