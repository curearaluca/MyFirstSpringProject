package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentEntityToStudentDtoMapper {

    public StudentDto mapStudentEntityToDto(StudentEntity studentEntity){
        return StudentDto.builder()
                .id(studentEntity.getId())
                .cnp(studentEntity.getCnp())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .build();
    }
}
