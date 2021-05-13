package com.myspringproject.university.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="dean")
public class DeanEntity {

    @Id
    private Integer id;

    @Column(name= "dean_cnp")
    private Long cnp;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name = "date_of_instalment")
    private LocalDate dateOfInstalment;

    private String title;
}
