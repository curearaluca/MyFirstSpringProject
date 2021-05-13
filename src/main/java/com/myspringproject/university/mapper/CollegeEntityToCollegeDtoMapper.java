package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.CollegeEntity;
import com.myspringproject.university.domain.model.CollegeDto;
import org.springframework.stereotype.Component;

@Component
public class CollegeEntityToCollegeDtoMapper {

    public CollegeDto mapCollegeEntityToDto(CollegeEntity collegeEntity){
        return CollegeDto.builder()
                .collegeId(collegeEntity.getCollegeId())
                .name(collegeEntity.getName())
                .build();
    }
}
