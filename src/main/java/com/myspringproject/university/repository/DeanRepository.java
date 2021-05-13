package com.myspringproject.university.repository;

import com.myspringproject.university.domain.entity.DeanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface DeanRepository extends JpaRepository<DeanEntity, Integer> {

    DeanEntity findByCnp(Long cnp);
}
