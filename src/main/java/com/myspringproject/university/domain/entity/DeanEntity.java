package com.myspringproject.university.domain.entity;

import com.myspringproject.university.domain.model.CollegeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="dean")
public class DeanEntity {

    @Id
    @Column(name= "dean_cnp")
    private BigInteger cnp;

    @Column(name= "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(name = "date_of_instalment")
    private LocalDate dateOfInstalment;

    @Column(name= "college_id")
    private Integer collegeId;

}
