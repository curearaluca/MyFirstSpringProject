package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.*;
import com.myspringproject.university.exception.FileNotFoundException;
import com.myspringproject.university.exception.StudentNotFoundException;
import com.myspringproject.university.mapper.StudentDtoCreateRequestToStudentEntityMapper;
import com.myspringproject.university.mapper.StudentEntityToStudentDtoMapper;
import com.myspringproject.university.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentEntityToStudentDtoMapper studentEntityToStudentDtoMapper;
    private final StudentDtoCreateRequestToStudentEntityMapper studentDtoCreateRequestToStudentEntityMapper;

    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents(){

        return studentRepository.findAll().stream()
                .map(studentEntity-> studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentEntity))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentById(Integer id){
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentRepository.findById(id).get());
    }

    @Transactional(readOnly = true)
    public StudentDto getStudentByCnp(Long cnp) {
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentRepository.findByCnp(cnp));
    }

    @Transactional
    public StudentDto createStudent(StudentDtoCreateRequest studentDto) {
        StudentEntity studentEntity = studentDtoCreateRequestToStudentEntityMapper.mapStudentDtoCreateRequestToEntity(studentDto);
        StudentEntity savedStudentEntity = studentRepository.save(studentEntity);
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(savedStudentEntity);
    }

    @Transactional
    public StudentDto updateStudent(StudentDtoUpdateRequest studentDto) {
        StudentEntity studentEntity = studentRepository.findById(studentDto.getId())
                .orElseThrow(()-> new StudentNotFoundException("Student not found for id "+ studentDto.getId()));
        studentEntity.setLastName(studentDto.getLastName());
        studentEntity.setFirstName(studentDto.getFirstName());
        if(studentDto.getMail() != null){
            studentEntity.setMail(studentDto.getMail());
        }
        if(studentDto.getFinalGrade() != null){
            studentEntity.setFinalGrade(studentDto.getFinalGrade());
        }
        StudentEntity updatedStudent = studentRepository.save(studentEntity);
        return studentEntityToStudentDtoMapper.mapStudentEntityToDto(updatedStudent);
    }

    @Transactional
    public List<StudentDto> createStudents(List<StudentDtoCreateRequest> studentsList) {
        return studentsList.stream()
                .map(student -> studentDtoCreateRequestToStudentEntityMapper.mapStudentDtoCreateRequestToEntity(student))
                .map(studentEntity -> studentRepository.save(studentEntity))
                .map(studentSaved -> studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentSaved))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    public List<StudentDto> createStudentsFromFile(MultipartFile file) {
        if(file.isEmpty()){
            throw new FileNotFoundException("File provided is empty");
        }
        byte[] bytes = file.getBytes();
        String fileContent = new String(bytes);
        String[] rows = fileContent.split("\r\n");
        List<StudentDtoCreateRequest> list= new ArrayList<>();
        for(String row: rows){
            String[] rowSplit=row.split(",");
            if(rowSplit.length != 0) {
                StudentDtoCreateRequest student=StudentDtoCreateRequest.builder()
                        .cnp(Long.valueOf(rowSplit[0]))
                        .firstName(rowSplit[1])
                        .lastName(rowSplit[2])
                        .collegeId(Integer.valueOf(rowSplit[3]))
                        .build();
                list.add(student);
            }
        }
        return createStudents(list);

    }

    public void deleteStudentById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }


}
