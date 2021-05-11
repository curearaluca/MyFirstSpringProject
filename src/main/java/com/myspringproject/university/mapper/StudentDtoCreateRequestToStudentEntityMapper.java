package com.myspringproject.university.mapper;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.ProfessorDtoCreateRequest;
import com.myspringproject.university.domain.model.StudentDtoCreateRequest;
import com.myspringproject.university.exception.CollegeNotFoundException;
import com.myspringproject.university.repository.CollegeRepository;
import com.myspringproject.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentDtoCreateRequestToStudentEntityMapper {

    private final CollegeRepository collegeRepository;

    public StudentEntity mapStudentDtoCreateRequestToEntity(StudentDtoCreateRequest studentDto){
        return StudentEntity.builder()
                .cnp(studentDto.getCnp())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .college(collegeRepository.findById(studentDto.getCollegeId())
                        .orElseThrow(()-> new CollegeNotFoundException("College id provided does not exist")))
                .build();
    }
}
