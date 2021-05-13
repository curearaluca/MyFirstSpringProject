package com.myspringproject.university.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="professor")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professor_id")
    private Integer id;

    @Column(name="professor_cnp")
    private Long cnp;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    private Integer salary;

    @Column(name="professor_mail")
    private String mail;

    @ManyToOne
    @JoinColumn(name= "college_id", referencedColumnName = "college_id")
    private CollegeEntity college;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<CourseEntity> courses= new ArrayList<>();

}
