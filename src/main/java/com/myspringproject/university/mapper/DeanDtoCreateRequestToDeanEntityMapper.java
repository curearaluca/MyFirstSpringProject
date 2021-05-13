package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.DeanEntity;
import com.myspringproject.university.domain.model.DeanDtoCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeanDtoCreateRequestToDeanEntityMapper {

    public DeanEntity mapDeanDtoPostRequestToEntity(DeanDtoCreateRequest deanDto, Integer collegeId){
        return DeanEntity.builder()
                .id(collegeId)
                .cnp(deanDto.getCnp())
                .firstName(deanDto.getFirstName())
                .lastName(deanDto.getLastName())
                .title(deanDto.getTitle())
                .dateOfInstalment(deanDto.getDateOfInstalment())
                .build();
    }
}
