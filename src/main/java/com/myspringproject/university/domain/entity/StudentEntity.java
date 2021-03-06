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
@Table(name="student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="student_id")
    private Integer id;

    @Column(name="student_cnp")
    private Long cnp;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="student_mail")
    private String mail;

    @Column(name="final_grade")
    private Double finalGrade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "univ_id", referencedColumnName = "college_id")
    private CollegeEntity college;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    private List<CourseEntity> courses = new ArrayList<>();
}
