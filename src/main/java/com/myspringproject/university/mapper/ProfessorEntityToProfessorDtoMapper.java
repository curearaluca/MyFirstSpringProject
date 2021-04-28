package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import org.springframework.stereotype.Component;

@Component
public class ProfessorEntityToProfessorDtoMapper {

    public ProfessorDto mapProfessorEntityToDto(ProfessorEntity professorEntity){
        return ProfessorDto.builder()
                .id(professorEntity.getId())
                .cnp(professorEntity.getCnp())
                .firstName(professorEntity.getFirstName())
                .lastName(professorEntity.getLastName())
                .build();
    }
}
