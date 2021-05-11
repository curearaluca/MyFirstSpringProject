package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.CollegeEntity;
import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.model.CollegeDto;
import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.domain.model.ProfessorDto;
import org.springframework.stereotype.Component;

@Component
public class CollegeEntityToCollegeDtoMapper {

    public CollegeDto mapCollegeEntityToDto(CollegeEntity collegeEntity){
        return CollegeDto.builder()
                .collegeId(collegeEntity.getCollegeId())
                .name(collegeEntity.getName())
                .dean(DeanDto.builder()
                        .id(collegeEntity.getDean().getId())
                        .firstName(collegeEntity.getDean().getFirstName())
                        .lastName(collegeEntity.getDean().getLastName())
                        .deanCnp(collegeEntity.getDean().getCnp())
                        .dateOfInstalment(collegeEntity.getDean().getDateOfInstalment())
                        .title(collegeEntity.getDean().getTitle())
                        .build())
                .build();
    }
}
