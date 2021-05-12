package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.DeanEntity;
import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.DeanDto;
import com.myspringproject.university.domain.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeanEntityToDeanDtoMapper {

    public DeanDto mapDeanEntityToDto(DeanEntity deanEntity){
        return DeanDto.builder()
                .id(deanEntity.getId())
                .deanCnp(deanEntity.getCnp())
                .firstName(deanEntity.getFirstName())
                .lastName(deanEntity.getLastName())
                .dateOfInstalment(deanEntity.getDateOfInstalment())
                .title(deanEntity.getTitle())
                .build();
    }
}
