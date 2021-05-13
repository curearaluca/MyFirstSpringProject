package com.myspringproject.university.repository;

import com.myspringproject.university.domain.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Integer> {

    List<CollegeEntity> findAll();
}
