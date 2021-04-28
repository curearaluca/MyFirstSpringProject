package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.ProfessorEntity;
import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.mapper.StudentDtoToStudentEntityMapper;
import com.myspringproject.university.mapper.StudentEntityToStudentDtoMapper;
import com.myspringproject.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentEntityToStudentDtoMapper studentEntityToStudentDtoMapper;
    private final StudentDtoToStudentEntityMapper studentDtoToStudentEntityMapper;

    public List<StudentDto> getAllStudents(){

        return studentRepository.findAll().stream()
                .map(studentEntity-> studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentEntity))
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Integer id){
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentRepository.findById(id).get());
    }

    public StudentDto getStudentByCnp(BigInteger cnp) {
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentRepository.findByCnp(cnp));
    }

    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity studentEntity = studentDtoToStudentEntityMapper.mapStudentDtoToEntity(studentDto);
        StudentEntity savedProfessorEntity= studentRepository.save(studentEntity);
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(savedProfessorEntity);
    }
}
