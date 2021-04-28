package com.myspringproject.university.repository;

import com.myspringproject.university.domain.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    List<StudentEntity> findAll();

    StudentEntity findStudentById(Integer id);

    StudentEntity findByCnp(BigInteger cnp);
}
