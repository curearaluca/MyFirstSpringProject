package com.myspringproject.university.service;

import com.myspringproject.university.domain.entity.StudentEntity;
import com.myspringproject.university.domain.model.ProfessorDto;
import com.myspringproject.university.domain.model.StudentDto;
import com.myspringproject.university.domain.model.StudentDtoUpdateRequest;
import com.myspringproject.university.mapper.StudentDtoCreateRequestToStudentEntityMapper;
import com.myspringproject.university.mapper.StudentEntityToStudentDtoMapper;
import com.myspringproject.university.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentEntityToStudentDtoMapper studentEntityToStudentDtoMapper;

    @Mock
    private StudentDtoCreateRequestToStudentEntityMapper studentDtoCreateRequestToStudentEntityMapper;

    @Test
    public void getAllStudents_givenNoStudent_thenReturnEmptyList(){
        Mockito.when(studentRepository.findAll()).thenReturn(List.of());
        List<StudentDto> allStudents = studentService.getAllStudents();
        Assertions.assertThat(allStudents).isNotNull();
        Assertions.assertThat(allStudents).isEmpty();
    }

    @Test
    public void getAllStudents_givenExistingStudents_thenReturnStudentsList(){
        List<StudentEntity> studentList = new ArrayList<>();
        StudentEntity studentEntity= StudentEntity.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        studentList.add(studentEntity);
        Mockito.when(studentRepository.findAll()).thenReturn(studentList);
        StudentDto studentDto= StudentDto.builder()
                .cnp(Long.valueOf("2222222222222"))
                .firstName("Test")
                .lastName("Test").build();
        Mockito.when(studentEntityToStudentDtoMapper.mapStudentEntityToDto(studentEntity)).thenReturn(studentDto);
        List<StudentDto> allStudents = studentService.getAllStudents();
        Assertions.assertThat(allStudents).isNotNull();
        Assertions.assertThat(allStudents.size()).isEqualTo(1);
        Assertions.assertThat(allStudents.get(0)).isNotNull();
        Assertions.assertThat(allStudents.get(0).getFirstName()).isEqualTo("Test");
    }

    @Test
    public void given_existing_student_when_updating_then_student_is_updated_and_returned() {
        int id = 23;
        var student = StudentDtoUpdateRequest.builder()
                .id(id)
                .firstName("Test")
                .lastName("Test")
                .mail("mail_test@gmail.com")
                .build();
        var studentEntityMock = Mockito.mock(StudentEntity.class);
        var studentDto = StudentDto.builder()
                .firstName("Test")
                .lastName("Test")
                .mail("mail_test@gmail.com")
                .build();
        Mockito.when(studentRepository.findById(id)).thenReturn(Optional.of(studentEntityMock));
        Mockito.when(studentEntityToStudentDtoMapper.mapStudentEntityToDto(ArgumentMatchers.any())).thenReturn(studentDto);
        var result = studentService.updateStudent(student);
        Mockito.verify(studentEntityMock).setFirstName(student.getFirstName());
        Mockito.verify(studentEntityMock).setLastName(student.getLastName());
        Mockito.verify(studentEntityMock).setMail(student.getMail());
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isSameAs(studentDto);
    }
}
