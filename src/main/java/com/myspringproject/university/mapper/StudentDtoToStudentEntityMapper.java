package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoToStudentEntityMapper {

    public StudentEntity mapStudentDtoToEntity(StudentDto studentDto){
        return StudentEntity.builder()
                .cnp(studentDto.getCnp())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .mail(studentDto.getMail())
                .finalGrade(studentDto.getFinalGrade())
                .build();
    }
}
