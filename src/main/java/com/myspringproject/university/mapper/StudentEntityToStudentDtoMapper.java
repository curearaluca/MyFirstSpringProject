package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.StudentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentEntityToStudentDtoMapper {

    private final CollegeEntityToCollegeDtoMapper collegeEntityToCollegeDtoMapper;

    public StudentDto mapStudentEntityToDto(StudentEntity studentEntity){
        return StudentDto.builder()
                .id(studentEntity.getId())
                .cnp(studentEntity.getCnp())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .college(collegeEntityToCollegeDtoMapper.mapCollegeEntityToDto(studentEntity.getCollege()))
                .build();
    }
}
