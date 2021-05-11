package com.myspringproject.university.domain.entity;

import com.myspringproject.university.domain.model.ProfessorDto;
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
@Table(name="course")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Integer id;

    @Column(name="course_name")
    private String name;

    private Integer credits;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "professor_id", referencedColumnName = "professor_id")
    private ProfessorEntity professor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "college_id", referencedColumnName = "college_id")
    private CollegeEntity college;

}
