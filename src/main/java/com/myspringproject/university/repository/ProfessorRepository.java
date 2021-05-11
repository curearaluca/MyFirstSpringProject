package com.myspringproject.university.repository;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Integer> {

    List<ProfessorEntity> findAll();

    ProfessorEntity findStudentById(Integer id);

    ProfessorEntity findByCnp(Long cnp);


}
