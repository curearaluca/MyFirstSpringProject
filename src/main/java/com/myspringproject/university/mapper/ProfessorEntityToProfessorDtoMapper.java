package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.CollegeDto;
import com.myspringproject.university.domain.model.ProfessorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfessorEntityToProfessorDtoMapper {
    private final CollegeEntityToCollegeDtoMapper collegeEntityToCollegeDtoMapper;

    public ProfessorDto mapProfessorEntityToDto(ProfessorEntity professorEntity){
        return ProfessorDto.builder()
                .id(professorEntity.getId())
                .cnp(professorEntity.getCnp())
                .firstName(professorEntity.getFirstName())
                .lastName(professorEntity.getLastName())
                .college(collegeEntityToCollegeDtoMapper.mapCollegeEntityToDto(professorEntity.getCollege()))
                .build();
    }
}
