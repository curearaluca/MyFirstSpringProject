package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDtoCreateRequest;
import com.myspringproject.university.exception.CollegeNotFoundException;
import com.myspringproject.university.repository.CollegeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfessorDtoCreateRequestToProfessorEntityMapper {

    private final CollegeRepository collegeRepository;

    public ProfessorEntity mapProfessorDtoPostRequestToEntity(ProfessorDtoCreateRequest professorDto){
        return ProfessorEntity.builder()
                .cnp(professorDto.getCnp())
                .firstName(professorDto.getFirstName())
                .lastName(professorDto.getLastName())
                .college(collegeRepository.findById(professorDto.getCollegeId())
                        .orElseThrow(()->new CollegeNotFoundException("College id provided does not exist")))
                .build();
    }
}
