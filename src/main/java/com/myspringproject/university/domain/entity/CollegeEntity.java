package com.myspringproject.university.domain.entity;

import com.myspringproject.university.domain.model.DeanDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="college")
public class CollegeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "college_id")
    private Integer collegeId;

    private String name;

    private String city;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "dean_id", referencedColumnName = "id")
    private DeanEntity dean;
}
