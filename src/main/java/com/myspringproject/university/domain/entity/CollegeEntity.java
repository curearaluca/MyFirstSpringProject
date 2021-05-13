package com.myspringproject.university.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "dean_id", referencedColumnName = "id")
    private DeanEntity dean;

}
