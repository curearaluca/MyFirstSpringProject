package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.CollegeEntity;
import com.myspringproject.university.domain.model.CollegeDtoCreateRequest;
import com.myspringproject.university.service.DeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollegeDtoCreateRequestToCollegeEntityMapper {

    public CollegeEntity mapCollegeDtoPostRequestToEntity(CollegeDtoCreateRequest collegeDto){
        return CollegeEntity.builder()
                .name(collegeDto.getName())
                .city(collegeDto.getCity())
                .build();
    }
}
