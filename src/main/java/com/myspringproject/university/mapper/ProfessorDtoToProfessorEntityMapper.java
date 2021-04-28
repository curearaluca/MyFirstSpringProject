package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import org.springframework.stereotype.Component;

@Component
public class ProfessorDtoToProfessorEntityMapper {

    public ProfessorEntity mapProfessorDtoToEntity(ProfessorDto professorDto){
        return ProfessorEntity.builder()
                .cnp(professorDto.getCnp())
                .firstName(professorDto.getFirstName())
                .lastName(professorDto.getLastName())
                .build();
    }
}
