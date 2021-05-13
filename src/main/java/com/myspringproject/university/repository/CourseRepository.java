package com.myspringproject.university.repository;

import com.myspringproject.university.domain.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

    List<CourseEntity> findAllByCollege_CollegeId(Integer college);
    List<CourseEntity> findAllByProfessor_Id(Integer professorId);
}
